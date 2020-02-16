package com.zwl.find.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Administrator on 2019/10/5 0005.
 */
@Configuration
@ImportResource(locations= {"classpath:application-solr.xml"})
public class SolrConfig {

}
