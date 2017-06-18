package com.litchi.analytics.tool.ecomercesdk;


import com.amazon.advertising.jaxws.AWSECommerceServicePortType;
import com.amazon.advertising.jaxws.ItemSearch;
import com.amazon.advertising.jaxws.ItemSearchRequest;
import com.amazon.advertising.jaxws.ItemSearchResponse;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingClient;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingInvoker;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingProperties;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingQueryPreparer;
import com.leechi.vendorclient.config.ConfigParser;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.ws.WebServiceException;
import java.math.BigInteger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AmzAdvertisingItemSearchTest {
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
    public void testSearchJavaInBooks() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setItemPage(new BigInteger("1"));
        request.setSearchIndex("Books");
        request.setKeywords("Java");

        final ItemSearch itemSearch = preparer.prepareItemSearch(request);

        ItemSearchResponse response = amzClient.invoke(new AmzAdvertisingInvoker<ItemSearchResponse>() {
            public ItemSearchResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.itemSearch(itemSearch);
            }
        });

        assertNotNull(response);
        assertTrue(response.getItems().size() > 0);
    }

    @Test
    public void testSearchJavaInAll() {
        ItemSearchRequest request = new ItemSearchRequest();
        request.setItemPage(new BigInteger("2"));
        request.setSearchIndex("All");
        request.setKeywords("Java");

        final ItemSearch itemSearch = preparer.prepareItemSearch(request);

        ItemSearchResponse response = amzClient.invoke(new AmzAdvertisingInvoker<ItemSearchResponse>() {
            public ItemSearchResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.itemSearch(itemSearch);
            }
        });

        assertNotNull(response);
        assertTrue(response.getItems().size() > 0);
    }
}
