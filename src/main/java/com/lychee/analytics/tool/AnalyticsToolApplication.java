package com.lychee.analytics.tool;

import com.lychee.analytics.tool.amazon.entity.AmzKeywordEntity;
import com.lychee.analytics.tool.amazon.service.AmzAutoCompleteService;
import com.lychee.analytics.tool.amazon.service.AmzCategoryMapService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class AnalyticsToolApplication {
	private static Logger logger = LoggerFactory.getLogger(AnalyticsToolApplication.class);
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
			String search="clear go";
			amzAutoCompleteService.SearchKeyword(search, result, Long.MAX_VALUE);
			//categoryMapService.buildCategoryRelationMap();
			logger.info("AnalyticsToolApplication has finished successfully");
		}catch (Exception ex) {
			logger.info("failed in AmzAutoCompleteService",ex);
		}
	}
}
