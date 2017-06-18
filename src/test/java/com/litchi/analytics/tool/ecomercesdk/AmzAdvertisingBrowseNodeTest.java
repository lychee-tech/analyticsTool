package com.litchi.analytics.tool.ecomercesdk;

import com.amazon.advertising.jaxws.AWSECommerceServicePortType;
import com.amazon.advertising.jaxws.BrowseNodeLookup;
import com.amazon.advertising.jaxws.BrowseNodeLookupRequest;
import com.amazon.advertising.jaxws.BrowseNodeLookupResponse;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingClient;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingInvoker;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingProperties;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingQueryPreparer;
import com.leechi.vendorclient.config.ConfigParser;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.ws.WebServiceException;


public class AmzAdvertisingBrowseNodeTest {
    private static AmzAdvertisingClient amzClient;
    private static AmzAdvertisingQueryPreparer preparer;

    @BeforeClass
    public static  void setup(){
        ConfigParser parser= new ConfigParser();
        parser.loadResourcePropertyFile("app-config");
        String endPoint = parser.getValue(AmzAdvertisingProperties.endPoint);
        String secret = parser.getValue(AmzAdvertisingProperties.secret);
        String accessKey = parser.getValue(AmzAdvertisingProperties.accessKeyId);
        String tag = parser.getValue(AmzAdvertisingProperties.tag);

        amzClient = AmzAdvertisingClient.builder().setAssociateSecret(secret).setAwsAdvertisingUrl(endPoint).build();
        preparer = AmzAdvertisingQueryPreparer.builder().setAwsAssociateAccessKey(accessKey).setAwsAssociateTag(tag).build();
    }


    @Test
    public void testSearchATopAmazonBrowseNode(){
        BrowseNodeLookupRequest request = new BrowseNodeLookupRequest();
        request.getBrowseNodeId().add("172282");

        final BrowseNodeLookup browseNodeLookup = preparer.prepareBrowseNodeLookup(request);
        BrowseNodeLookupResponse response= amzClient.invoke(new AmzAdvertisingInvoker<BrowseNodeLookupResponse>() {
            public BrowseNodeLookupResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.browseNodeLookup(browseNodeLookup);
            }
        });

    }
}
