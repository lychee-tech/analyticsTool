package com.lychee.analytics.tool.amazon.service;


import com.amazon.advertising.jaxws.AWSECommerceServicePortType;
import com.amazon.advertising.jaxws.ItemSearch;
import com.amazon.advertising.jaxws.ItemSearchRequest;
import com.amazon.advertising.jaxws.ItemSearchResponse;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingClient;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingInvoker;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingProperties;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingQueryPreparer;
import com.leechi.vendorclient.config.ConfigParser;
import com.lychee.analytics.tool.amazon.model.AmzProductPage;
import com.lychee.analytics.tool.amazon.model.AmzProductSummary;
import org.springframework.stereotype.Service;

import javax.xml.ws.WebServiceException;
import java.math.BigInteger;

@Service
public class AmzAdvertingService {
    private static AmzAdvertisingClient amzClient;
    private static AmzAdvertisingQueryPreparer preparer;

    static {
        ConfigParser parser= new ConfigParser();
        parser.loadResourcePropertyFile("app-config");
        String endPoint = parser.getValue(AmzAdvertisingProperties.endPoint);
        String secret = parser.getValue(AmzAdvertisingProperties.secret);
        String accessKey = parser.getValue(AmzAdvertisingProperties.accessKeyId);
        String tag = parser.getValue(AmzAdvertisingProperties.tag);

        amzClient = AmzAdvertisingClient.builder().setAssociateSecret(secret).setAwsAdvertisingUrl(endPoint).build();
        preparer = AmzAdvertisingQueryPreparer.builder().setAwsAssociateAccessKey(accessKey).setAwsAssociateTag(tag).build();
    }

    public AmzProductPage searchProduct(String keyword, Integer pageNumber) {
        AmzProductPage page = new AmzProductPage();

        ItemSearchRequest request = new ItemSearchRequest();
        request.setItemPage(new BigInteger(pageNumber.toString()));
        request.setSearchIndex("All");
        request.setKeywords(keyword);

        final ItemSearch itemSearch = preparer.prepareItemSearch(request);

        ItemSearchResponse response = amzClient.invoke(new AmzAdvertisingInvoker<ItemSearchResponse>() {
            public ItemSearchResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.itemSearch(itemSearch);
            }
        });


        if (response.getItems()==null || response.getItems().size() ==0) {
            page.setCount(BigInteger.ZERO);
            return page;
        }

        com.amazon.advertising.jaxws.Items items= response.getItems().get(0);
        page.setCount(items.getTotalResults());
        for(com.amazon.advertising.jaxws.Item item: items.getItem()) {
            AmzProductSummary productSummary = new AmzProductSummary();
            productSummary.setAsin(item.getASIN());
            productSummary.setTitle(item.getItemAttributes().getTitle());
            productSummary.setDetailUrl(item.getDetailPageURL());

            page.getProducts().add(productSummary);
        }

        return page;
    }

}
