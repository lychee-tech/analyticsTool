package com.litchi.analytics.tool.amazon.help;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.litchi.analytics.tool.amazon.model.AmzBrowseNode;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class AmzTopBrowsNodeHelp {
    private static Logger logger = Logger.getLogger(AmzTopBrowsNodeHelp.class);
    private static Map<String, AmzBrowseNode> topNodeDict;
    static {
        try {
            File file = new ClassPathResource("amzTopBrowseNodes.json").getFile();
            String content = new String( Files.readAllBytes(file.toPath()));

            ObjectMapper mapper = new ObjectMapper();
            Map<Object, Object> map = mapper.readValue(content, Map.class);
            topNodeDict = new HashMap<>();
            map.forEach((k,v)->{
                String name  = k.toString().trim();
                String browseId = v.toString();
                topNodeDict.put(browseId, new AmzBrowseNode(browseId,name,false));
            });

        } catch (Exception ex) {
            logger.error("failed to load amazon top browse nodes from amzTopBrowseNodes.json", ex);
        }
    }

    public static Map<String, AmzBrowseNode> getTopNodeDict(){
        return topNodeDict;
    }
}
