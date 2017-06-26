package com.litchi.analytics.tool.amazon.help;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.litchi.analytics.tool.amazon.model.AmzBrowseNode;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AmzTopBrowsNodeHelp {
    private static Logger logger = LoggerFactory.getLogger(AmzTopBrowsNodeHelp.class);
    private static Map<String, AmzBrowseNode> topNodeDict;
    static {
        try {
            InputStream stream = new ClassPathResource("amzTopBrowseNodes.json").getInputStream();
            String content = IOUtils.toString(stream, StandardCharsets.UTF_8);

            ObjectMapper mapper = new ObjectMapper();
            Map<Object, Object> map = mapper.readValue(content, Map.class);
            topNodeDict = new HashMap<>();
            map.forEach((k,v)->{
                String name  = k.toString().trim();
                String browseId = v.toString();
                topNodeDict.put(browseId, new AmzBrowseNode(browseId,name,false));
            });
            logger.info("loaded amazon to browse node from amzTopBrowseNodes.json");

        } catch (Exception ex) {
            logger.error("failed to load amazon top browse nodes from amzTopBrowseNodes.json", ex);
        }
    }

    public static Map<String, AmzBrowseNode> getTopNodeDict(){
        return topNodeDict;
    }
}
