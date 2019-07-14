/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.elasticjob.lite.console.restful.config;

import io.elasticjob.lite.console.domain.EventTraceDataSourceConfiguration;
import io.elasticjob.lite.console.domain.EventTraceDataSourceFactory;
import io.elasticjob.lite.console.service.EventTraceDataSourceConfigurationService;
import io.elasticjob.lite.console.service.impl.EventTraceDataSourceConfigurationServiceImpl;
import io.elasticjob.lite.console.util.SessionEventTraceDataSourceConfiguration;
import com.google.common.base.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®çš„RESTful API.
 *
 * @author caohao
 */
@Path("/data-source")
public final class EventTraceDataSourceRestfulApi {
    
    public static final String DATA_SOURCE_CONFIG_KEY = "data_source_config_key";
    
    private EventTraceDataSourceConfigurationService eventTraceDataSourceConfigurationService = new EventTraceDataSourceConfigurationServiceImpl();
    
    /**
     * åˆ¤æ–­æ˜¯å?¦å­˜åœ¨å·²è¿žæŽ¥çš„äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®.
     *
     * @param request HTTPè¯·æ±‚
     * @return æ˜¯å?¦å­˜åœ¨å·²è¿žæŽ¥çš„äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®
     */
    @GET
    @Path("/activated")
    public boolean activated(final @Context HttpServletRequest request) {
        return eventTraceDataSourceConfigurationService.loadActivated().isPresent();
    }
    
    /**
     * è¯»å?–äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®.
     * 
     * @param request HTTPè¯·æ±‚å¯¹è±¡
     * @return äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®é›†å?ˆ
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EventTraceDataSourceConfiguration> load(final @Context HttpServletRequest request) {
        Optional<EventTraceDataSourceConfiguration> dataSourceConfig = eventTraceDataSourceConfigurationService.loadActivated();
        if (dataSourceConfig.isPresent()) {
            setDataSourceNameToSession(dataSourceConfig.get(), request.getSession());
        }
        return eventTraceDataSourceConfigurationService.loadAll().getEventTraceDataSourceConfiguration();
    }
    
    /**
     * æ·»åŠ äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®.
     * 
     * @param config äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®
     * @return æ˜¯å?¦æ·»åŠ æˆ?åŠŸ
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(final EventTraceDataSourceConfiguration config) {
        return eventTraceDataSourceConfigurationService.add(config);
    }
    
    /**
     * åˆ é™¤äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®.
     * 
     * @param config äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(final EventTraceDataSourceConfiguration config) {
        eventTraceDataSourceConfigurationService.delete(config.getName());
    }
    
    /**
     * è¿žæŽ¥äº‹ä»¶è¿½è¸ªæ•°æ?®æº?æµ‹è¯•.
     *
     * @param config äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®
     * @param request HTTPè¯·æ±‚å¯¹è±¡
     * @return æ˜¯å?¦è¿žæŽ¥æˆ?åŠŸ
     */
    @POST
    @Path("/connectTest")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean connectTest(final EventTraceDataSourceConfiguration config, final @Context HttpServletRequest request) {
        return setDataSourceNameToSession(config, request.getSession());
    }
    
    /**
     * è¿žæŽ¥äº‹ä»¶è¿½è¸ªæ•°æ?®æº?.
     *
     * @param config äº‹ä»¶è¿½è¸ªæ•°æ?®æº?é…?ç½®
     * @param request HTTPè¯·æ±‚å¯¹è±¡
     * @return æ˜¯å?¦è¿žæŽ¥æˆ?åŠŸ
     */
    @POST
    @Path("/connect")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean connect(final EventTraceDataSourceConfiguration config, final @Context HttpServletRequest request) {
        boolean isConnected = setDataSourceNameToSession(eventTraceDataSourceConfigurationService.find(config.getName(), eventTraceDataSourceConfigurationService.loadAll()), request.getSession());
        if (isConnected) {
            eventTraceDataSourceConfigurationService.load(config.getName());
        }
        return isConnected;
    }
    
    private boolean setDataSourceNameToSession(final EventTraceDataSourceConfiguration dataSourceConfig, final HttpSession session) {
        session.setAttribute(DATA_SOURCE_CONFIG_KEY, dataSourceConfig);
        try {
            EventTraceDataSourceFactory.createEventTraceDataSource(dataSourceConfig.getDriver(), dataSourceConfig.getUrl(), 
                    dataSourceConfig.getUsername(), Optional.fromNullable(dataSourceConfig.getPassword()));
            SessionEventTraceDataSourceConfiguration.setDataSourceConfiguration((EventTraceDataSourceConfiguration) session.getAttribute(DATA_SOURCE_CONFIG_KEY));
        // CHECKSTYLE:OFF
        } catch (final Exception ex) {
        // CHECKSTYLE:ON
            return false;
        }
        return true;
    }
}
