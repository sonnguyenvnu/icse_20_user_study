package org.hswebframework.web.workflow.web.diagram;

import org.hswebframework.web.authorization.annotation.Authorize;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouhao
 * @since 3.0.0-RC
 */
@RestController
@RequestMapping("/workflow/service/")
@Authorize(permission = "workflow-definition", description = "å·¥ä½œæµ?-æµ?ç¨‹å®šä¹‰ç®¡ç?†")
public class ProcessInstanceDiagramLayoutResource
        extends BaseProcessDefinitionDiagramLayoutResource
{


    @GetMapping(
            value = {"/process-instance/{processInstanceId}/diagram-layout"},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Object getDiagram(@PathVariable String processInstanceId) {
        return this.getDiagramNode(processInstanceId, null);
    }
}
