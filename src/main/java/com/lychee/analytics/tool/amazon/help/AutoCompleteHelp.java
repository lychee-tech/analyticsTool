package com.lychee.analytics.tool.amazon.help;


import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * help class for query keyword using amazon autocomplete api.
 *   the autocomplete api has result like :
 *     completion = ["a",["aa batteries","aaa batteries","amazon fire stick","american flag","apple watch band","apple watch band 38mm","alexa","apple watch band 42mm","air mattress","activated charcoal"],[{"nodes":[{"name":"Electronics","alias":"electronics"},{"name":"Health, Household & Baby Care","alias":"hpc"}]},{},{},{},{},{},{},{},{},{}],[],"SFIRD76IME36"];updateISSCompletion();
 *
 *
 */
public class AutoCompleteHelp {
   private static Logger logger = LoggerFactory.getLogger(AutoCompleteHelp.class);
   private static  URLCodec codec = new URLCodec();
   private static Client client =  ClientBuilder.newClient();
   private static String urlTemplate ="http://completion.amazon.com/search/complete?method=completion&q=%s&search-alias=aps&client=amazon-search-ui&mkt=1&x=updateISSCompletion&sc=1&noCacheIE=1294493634389";



   public static List<String> getAutoCompleteMatches(String keyword) throws Exception  {
      String url = String.format(urlTemplate, codec.encode(keyword));

      WebTarget webTarget = client.target(url);

      Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN);
      Response response = invocationBuilder.get();

      String body=response.readEntity(String.class);
      if (StringUtils.isBlank(body)) {
         return new ArrayList<String>();
      }

      String value = body.split("=")[1].trim();
      int p1=value.indexOf('[', 1);
      int p2 = value.indexOf(']', 1);

      try {
         String sub = value.substring(p1+1,p2);
         return parseAutoCompleteResultArray(sub);

      } catch (Exception ex) {
         String error = String.format("amazon autocomplete response %s not null, but failed to get the response ", value);
         logger.error(error, ex);
         throw ex;
      }
   }

   /**
    * parse autocomplete response
    *   the response has format "a","b","c"
    *
    * @param value
    * @return
    */
   private static List<String> parseAutoCompleteResultArray(String value){
      List<String> result = new ArrayList<String>();
      String[] parts = value.split(",");
      if (parts!=null && parts.length>0) {
         for(String p: parts) {
            if (!StringUtils.isBlank(p)) result.add(p.trim());
         }
      }

      return result;
   }
}
