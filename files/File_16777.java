package org.hswebframework.web.workflow.web;

import io.swagger.annotations.Api;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.SimpleGenericEntityController;
import org.hswebframework.web.service.CrudService;
import org.hswebframework.web.workflow.dao.entity.ProcessDefineConfigEntity;
import org.hswebframework.web.workflow.service.ProcessDefineConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhao
 * @since 3.0.0-RC
 */
@RestController
@RequestMapping("/workflow/process/configuration/definition")
@Authorize(permission = "workflow-definition", description = "å·¥ä½œæµ?-æµ?ç¨‹å®šä¹‰ç®¡ç?†")
@Api(tags = "å·¥ä½œæµ?-æµ?ç¨‹å®šä¹‰-æµ?ç¨‹é…?ç½®")
public class ProcessDefineConfigController
        implements SimpleGenericEntityController<ProcessDefineConfigEntity, String, QueryParamEntity> {
    @Autowired
    private ProcessDefineConfigService service;

    @Override
    public CrudService<ProcessDefineConfigEntity, String> getService() {
        return service;
    }

}
