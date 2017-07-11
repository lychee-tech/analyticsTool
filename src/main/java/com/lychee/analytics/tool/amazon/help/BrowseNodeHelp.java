package com.lychee.analytics.tool.amazon.help;


import com.amazon.advertising.jaxws.*;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingClient;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingInvoker;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingProperties;
import com.leechi.vendorclient.amzAdvertising.AmzAdvertisingQueryPreparer;
import com.leechi.vendorclient.config.ConfigParser;
import com.lychee.analytics.tool.amazon.model.AmzBrowseNode;

import javax.xml.ws.WebServiceException;
import java.util.List;

public class BrowseNodeHelp {
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


    public static AmzBrowseNode getBrowseNode(String browseId, List<AmzBrowseNode> children) {
        BrowseNodeLookupRequest request = new BrowseNodeLookupRequest();
        request.getBrowseNodeId().add(browseId);

        final BrowseNodeLookup browseNodeLookup = preparer.prepareBrowseNodeLookup(request);
        BrowseNodeLookupResponse response= amzClient.invoke(new AmzAdvertisingInvoker<BrowseNodeLookupResponse>() {
            public BrowseNodeLookupResponse invoke(AWSECommerceServicePortType port) throws WebServiceException {
                return port.browseNodeLookup(browseNodeLookup);
            }
        });

        if (response == null || response.getBrowseNodes() == null && response.getBrowseNodes().size() ==0) {
            return null;
        }

        BrowseNodes nodes = response.getBrowseNodes().get(0);
        if (nodes == null || nodes.getBrowseNode() == null || nodes.getBrowseNode().size() ==0){
            return null;
        }

        BrowseNode node = nodes.getBrowseNode().get(0);
        AmzBrowseNode amzBrowseNode  = new AmzBrowseNode(node.getBrowseNodeId(),node.getName(), node.isIsCategoryRoot());

        if (node.getChildren()!=null && node.getChildren().getBrowseNode()!=null) {
            node.getChildren().getBrowseNode().forEach(c-> {
                children.add(new AmzBrowseNode(c.getBrowseNodeId(), c.getName(),c.isIsCategoryRoot()));
            });
        }


        return amzBrowseNode;
    }
}
