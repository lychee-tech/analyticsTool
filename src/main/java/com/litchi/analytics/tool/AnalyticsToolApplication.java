package com.litchi.analytics.tool;

import com.litchi.analytics.tool.amazon.entity.AmzKeywordEntity;
import com.litchi.analytics.tool.amazon.service.AmzAutoCompleteService;
import com.litchi.analytics.tool.amazon.service.AmzCategoryMapService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class AnalyticsToolApplication {
	private static Logger logger = LogManager.getLogger(AnalyticsToolApplication.class);
	@Autowired
	private AmzAutoCompleteService amzAutoCompleteService;
	@Autowired
	private AmzCategoryMapService categoryMapService;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder()
				.sources(AnalyticsToolApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);


		AnalyticsToolApplication app = context.getBean(AnalyticsToolApplication.class);
		app.start();
	}

	private void start() {
		List<AmzKeywordEntity> result = new ArrayList<>();
		try {
			amzAutoCompleteService.SearchKeyword("", result,Long.MAX_VALUE);
			//categoryMapService.buildCategoryRelationMap();
		}catch (Exception ex) {
			logger.error("failed in AmzAutoCompleteService",ex);
		}
	}
}
