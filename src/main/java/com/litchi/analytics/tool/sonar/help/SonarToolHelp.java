package com.litchi.analytics.tool.sonar.help;


import com.litchi.analytics.tool.sonar.model.SonarMatchResult;
import com.litchi.analytics.tool.sonar.model.SonarSearchRequest;


import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SonarToolHelp {
    private static Client client =  ClientBuilder.newClient();
    private static String url ="http://sonar.sellics.com:9191/search";
    private static WebTarget webTarget;

    static {
        webTarget = client.target(url);
    }

    public static SonarMatchResult getSonarToolMatches(SonarSearchRequest request){

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.accept(MediaType.APPLICATION_JSON).post(Entity.entity(request,  MediaType.APPLICATION_JSON), Response.class);

        if (response.getStatus() !=200) {
            throw new RuntimeException(String.format("sonar-tool returns error for keword %s", request.getKeyword()));
        } else {
            return response.readEntity(SonarMatchResult.class);
        }
    }
}
