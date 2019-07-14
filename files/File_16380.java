package org.hswebframework.web.controller.form;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.entity.form.DynamicFormColumnEntity;
import org.hswebframework.web.logging.AccessLogger;
import org.hswebframework.web.service.form.DynamicFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * åŠ¨æ€?è¡¨å?•
 *
 * @author hsweb-generator-online
 */
@RestController
@RequestMapping("${hsweb.web.mappings.dynamic-form-column:dynamic/form/column}")
@Authorize(permission = "dynamic-form", description = "åŠ¨æ€?è¡¨å?•ç®¡ç?†")
@Api(value = "åŠ¨æ€?è¡¨å?•ç®¡ç?†",tags = "åŠ¨æ€?è¡¨å?•-è¡¨å?•ç®¡ç?†")
public class DynamicFormColumnController {

    private DynamicFormService dynamicFormService;

    @Autowired
    public void setDynamicFormService(DynamicFormService dynamicFormService) {
        this.dynamicFormService = dynamicFormService;
    }

    @PatchMapping("/batch")
    @Authorize(action = Permission.ACTION_ADD)
    @ApiOperation("ä¿?å­˜å¤šä¸ªè¡¨å?•åˆ—")
    public ResponseMessage<List<String>> add(@RequestBody List<DynamicFormColumnEntity> columnEntities) {
        return ResponseMessage.ok(dynamicFormService.saveOrUpdateColumn(columnEntities));
    }

    @PatchMapping
    @Authorize(action = Permission.ACTION_ADD)
    @ApiOperation("ä¿?å­˜è¡¨å?•åˆ—")
    public ResponseMessage<String> add(@RequestBody DynamicFormColumnEntity columnEntity) {
        return ResponseMessage.ok(dynamicFormService.saveOrUpdateColumn(columnEntity));
    }

    @DeleteMapping
    @Authorize(action = Permission.ACTION_DELETE)
    @ApiOperation("åˆ é™¤åˆ—")
    public ResponseMessage<List<DynamicFormColumnEntity>> delete(@ApiParam(value = "è¦?åˆ é™¤çš„åˆ—id,å¤šä¸ªåˆ—ä»¥,åˆ†å‰²", example = "1,2,3")
                                                                 @RequestParam String ids) {
        return ResponseMessage.ok(dynamicFormService.deleteColumn(Arrays.asList(ids.split("[,]"))));
    }

    @GetMapping("/{formId}")
    @Authorize(action = Permission.ACTION_GET)
    @ApiOperation("èŽ·å?–è¡¨å?•çš„æ‰€æœ‰åˆ—")
    public ResponseMessage<List<DynamicFormColumnEntity>> getByFormId(@PathVariable String formId) {
        return ResponseMessage.ok(dynamicFormService.selectColumnsByFormId(formId));
    }

}
