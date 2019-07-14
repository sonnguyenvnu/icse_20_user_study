package org.hswebframework.web.workflow.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.hswebframework.ezorm.core.Conditional;
import org.hswebframework.ezorm.core.NestConditional;
import org.hswebframework.ezorm.core.dsl.Query;
import org.hswebframework.web.NotFoundException;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.commons.entity.PagerResult;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.workflow.service.BpmActivityService;
import org.hswebframework.web.workflow.service.WorkFlowFormService;
import org.hswebframework.web.workflow.service.config.CandidateInfo;
import org.hswebframework.web.workflow.service.config.ProcessConfigurationService;
import org.hswebframework.web.workflow.service.BpmProcessService;
import org.hswebframework.web.workflow.service.BpmTaskService;
import org.hswebframework.web.workflow.service.request.CompleteTaskRequest;
import org.hswebframework.web.workflow.service.request.JumpTaskRequest;
import org.hswebframework.web.workflow.service.request.RejectTaskRequest;
import org.hswebframework.web.workflow.service.request.StartProcessRequest;
import org.hswebframework.web.workflow.util.QueryUtils;
import org.hswebframework.web.workflow.web.response.CandidateDetail;
import org.hswebframework.web.workflow.web.response.ProcessInfo;
import org.hswebframework.web.workflow.web.response.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author zhouhao
 * @since 3.0.0-RC
 */
@RestController
@RequestMapping("/workflow/process")
@Api(tags = "å·¥ä½œæµ?-æµ?ç¨‹ç®¡ç?†", description = "å·¥ä½œæµ?-æµ?ç¨‹ç®¡ç?†")
@Authorize(permission = "workflow-process", description = "å·¥ä½œæµ?-æµ?ç¨‹ç®¡ç?†")
public class FlowableProcessController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private BpmTaskService bpmTaskService;

    @Autowired
    private BpmActivityService bpmActivityService;

    @Autowired
    private BpmProcessService bpmProcessService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessConfigurationService processConfigurationService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private WorkFlowFormService workFlowFormService;

    @GetMapping("/doing")
    @Authorize(action = Permission.ACTION_QUERY)
    @ApiOperation("æŸ¥è¯¢è¿›è¡Œä¸­çš„æµ?ç¨‹ä¿¡æ?¯")
    public ResponseMessage<PagerResult<ProcessInfo>> queryProcess(QueryParamEntity query) {
        ProcessInstanceQuery instanceQuery = runtimeService.createProcessInstanceQuery();

        PagerResult<ProcessInfo> result = QueryUtils.doQuery(instanceQuery, query, ProcessInfo::of, (term, q) -> {
            if ("status".equals(term.getColumn())) {
                switch (String.valueOf(term.getValue())) {
                    case "active":
                        q.active();
                        break;
                    case "suspended":
                        q.suspended();
                        break;
                    default:
                        break;
                }
            }
        });

        return ResponseMessage.ok(result).exclude(query.getExcludes()).include(query.getIncludes());
    }

    @GetMapping("/tasks")
    @Authorize(action = Permission.ACTION_QUERY)
    @ApiOperation("æŸ¥è¯¢å½“å‰?ç”¨æˆ·çš„åŽ†å?²ä»»åŠ¡ä¿¡æ?¯")
    public ResponseMessage<PagerResult<TaskInfo>> getHistory(QueryParamEntity query, Authentication authentication) {
        HistoricTaskInstanceQuery historyQuery = historyService.createHistoricTaskInstanceQuery();
        historyQuery.taskAssignee(authentication.getUser().getId());

        PagerResult<TaskInfo> result = QueryUtils.doQuery(historyQuery, query, TaskInfo::of, (term, q) -> {
            if ("status".equals(term.getColumn())) {
                switch (String.valueOf(term.getValue())) {
                    case "finished":
                        q.finished();
                        break;
                    case "processFinished":
                        q.processFinished();
                        break;
                    default:
                        break;
                }
            }
        });

        return ResponseMessage.ok(result).exclude(query.getExcludes()).include(query.getIncludes());
    }

    @PostMapping("/start/key/{defineKey}")
    @ApiOperation("æ??äº¤è¡¨å?•æ•°æ?®å¹¶æ ¹æ?®æµ?ç¨‹å®šä¹‰keyå?¯åŠ¨æµ?ç¨‹")
    @Authorize(merge = false)
    public ResponseMessage<String> startProcessByKey(@PathVariable String defineKey,
                                                     @RequestBody Map<String, Object> data,
                                                     Authentication authentication) {
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(defineKey)
                .active()
                .latestVersion()
                .singleResult();

        if (null == definition) {
            throw new NotFoundException("æµ?ç¨‹[" + defineKey + "]ä¸?å­˜åœ¨");
        }
        //åˆ¤æ–­æ?ƒé™?
        processConfigurationService.getProcessConfiguration(definition.getId())
                .assertCanStartProcess(authentication.getUser().getId(), definition);

        String id = definition.getId();

        ProcessInstance instance = bpmProcessService.startProcessInstance(StartProcessRequest.builder()
                .creatorId(authentication.getUser().getId())
                .creatorName(authentication.getUser().getName())
                .formData(data)
                .processDefineId(id)
                .build());


        return ResponseMessage.ok(instance.getId());
    }

    @PostMapping("/start/id/{defId}")
    @ApiOperation("æ??äº¤è¡¨å?•æ•°æ?®å¹¶æ ¹æ?®æµ?ç¨‹å®šä¹‰IDå?¯åŠ¨æµ?ç¨‹")
    @Authorize(merge = false)
    public ResponseMessage<String> startProcess(@PathVariable String defId,
                                                @RequestBody Map<String, Object> data,
                                                Authentication authentication) {
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(defId)
                .active()
                .singleResult();

        if (null == definition) {
            throw new NotFoundException("æµ?ç¨‹[" + defId + "]ä¸?å­˜åœ¨");
        }
        //åˆ¤æ–­æ?ƒé™?
        processConfigurationService.getProcessConfiguration(definition.getId())
                .assertCanStartProcess(authentication.getUser().getId(), definition);


        ProcessInstance instance = bpmProcessService.startProcessInstance(StartProcessRequest.builder()
                .creatorId(authentication.getUser().getId())
                .creatorName(authentication.getUser().getName())
                .formData(data)
                .processDefineId(defId)
                .build());

        return ResponseMessage.ok(instance.getId());
    }

    @GetMapping("/todo")
    @ApiOperation("èŽ·å?–å¾…åŠžä»»åŠ¡")
    @Authorize(merge = false)
    public ResponseMessage<PagerResult<TaskInfo>> getTodoList(QueryParamEntity query, Authentication authentication) {
        TaskQuery taskQuery = taskService.createTaskQuery();

        taskQuery.taskAssignee(authentication.getUser().getId());

        PagerResult<TaskInfo> result = QueryUtils.doQuery(taskQuery, query, TaskInfo::of);


        return ResponseMessage.ok(result).exclude(query.getExcludes()).include(query.getIncludes());
    }

    @AllArgsConstructor
    @Getter
    public enum Type {
        claim("user-wf-claim"),
        todo("user-wf-todo"),
        completed("user-wf-completed"),
        part("user-wf-part"),
        create("is") {
            @Override
            public void applyQueryTerm(NestConditional<?> conditional, String userId) {
                conditional.accept("creatorId", getTermType(), userId);
            }
        },
        claimOrTodo("is") {
            @Override
            public void applyQueryTerm(NestConditional<?> conditional, String userId) {
                conditional.nest()
                        .when(true, q -> Type.claim.applyQueryTerm(q, userId))
                        .or()
                        .when(true, q -> Type.todo.applyQueryTerm(q, userId))
                        .end();
            }
        };

        private String termType;

        public void applyQueryTerm(NestConditional<?> conditional, String userId) {
            conditional.accept("processInstanceId", termType, userId);
        }
    }

    @GetMapping("/{type}/form/{processDefineId}")
    @ApiOperation("èŽ·å?–è‡ªå·±å?¯æŸ¥çœ‹çš„æµ?ç¨‹è¡¨å?•æ•°æ?®")
    @Authorize(merge = false)
    @SuppressWarnings("all")
    public ResponseMessage<PagerResult<Object>> getFormData(@PathVariable Type type,
                                                            @PathVariable String processDefineId,
                                                            QueryParamEntity query,
                                                            Authentication authentication) {
        Query.empty(query)
                .nest()
                .when(type != null, q -> type.applyQueryTerm(q, authentication.getUser().getId()))
                .end();
        return ResponseMessage.ok(workFlowFormService.selectProcessForm(processDefineId, query));
    }

    @GetMapping("/task/form/{processDefineId}/{taskDefineKey}")
    @ApiOperation("èŽ·å?–æµ?ç¨‹ä»»åŠ¡è¡¨å?•æ•°æ?®")
    @Authorize(merge = false)
    public ResponseMessage<PagerResult<Object>> getTaskFormData(@PathVariable String processDefineId,
                                                                @PathVariable String taskDefineKey,
                                                                QueryParamEntity query) {
        return ResponseMessage.ok(workFlowFormService.selectTaskForm(processDefineId, taskDefineKey, query));
    }


    @GetMapping("/claims")
    @ApiOperation("èŽ·å?–å¾…ç­¾æ”¶ä»»åŠ¡")
    @Authorize(merge = false)
    public ResponseMessage<PagerResult<TaskInfo>> getClaims(QueryParamEntity query, Authentication authentication) {
        TaskQuery taskQuery = taskService.createTaskQuery();

        taskQuery.taskCandidateUser(authentication.getUser().getId());

        PagerResult<TaskInfo> result = QueryUtils.doQuery(taskQuery, query, TaskInfo::of);

        return ResponseMessage.ok(result);
    }

    @GetMapping("/claims-and-todo")
    @ApiOperation("èŽ·å?–å¾…ç­¾æ”¶å’Œå¾…å¤„ç?†çš„ä»»åŠ¡")
    @Authorize(merge = false)
    public ResponseMessage<PagerResult<TaskInfo>> getClaimsAndTodo(QueryParamEntity query, Authentication authentication) {
        TaskQuery taskQuery = taskService.createTaskQuery();

        taskQuery.taskCandidateOrAssigned(authentication.getUser().getId());

        PagerResult<TaskInfo> result = QueryUtils.doQuery(taskQuery, query, TaskInfo::of);

        return ResponseMessage.ok(result);
    }

    @PutMapping("/claim/{taskId}")
    @ApiOperation("ç­¾æ”¶ä»»åŠ¡")
    @Authorize(merge = false)
    public ResponseMessage<Void> claim(@PathVariable String taskId, Authentication authentication) {
        bpmTaskService.claim(taskId, authentication.getUser().getId());
        return ResponseMessage.ok();
    }

    @PutMapping("/complete/{taskId}")
    @Authorize(merge = false)
    public ResponseMessage<Void> complete(@PathVariable String taskId,
                                          @RequestBody(required = false) Map<String, Object> formData,
                                          Authentication authentication) {
        // åŠžç?†
        bpmTaskService.complete(CompleteTaskRequest.builder()
                .taskId(taskId)
                .completeUserId(authentication.getUser().getId())
                .completeUserName(authentication.getUser().getName())
                .formData(formData)
                .build());
        return ResponseMessage.ok();
    }

    @PutMapping("/reject/{taskId}")
    @Authorize(merge = false)
    @ApiOperation("é©³å›ž")
    public ResponseMessage<Void> reject(@PathVariable String taskId,
                                        @RequestBody Map<String, Object> data,
                                        Authentication authentication) {
        // é©³å›ž
        bpmTaskService.reject(RejectTaskRequest.builder()
                .taskId(taskId)
                .rejectUserId(authentication.getUser().getId())
                .rejectUserName(authentication.getUser().getName())
                .data(data)
                .build());
        return ResponseMessage.ok();
    }

    @PutMapping("/jump/{taskId}/{activityId}")
    @Authorize(merge = false)
    @ApiOperation("æµ?ç¨‹è·³è½¬")
    public ResponseMessage<Void> jump(@PathVariable String taskId,
                                      @PathVariable String activityId,
                                      @RequestBody Map<String, Object> data,
                                      Authentication authentication) {
        // æµ?ç¨‹è·³è½¬
        bpmTaskService.jumpTask(JumpTaskRequest
                .builder()
                .taskId(taskId)
                .targetActivityId(activityId)
                .recordLog(true)
                .jumpUserId(authentication.getUser().getId())
                .jumpUserName(authentication.getUser().getUsername())
                .data(data)
                .build());
        return ResponseMessage.ok();
    }

    @PostMapping("/next-task-candidate/{taskId}")
    @Authorize(merge = false)
    public ResponseMessage<List<CandidateDetail>> candidateList(@PathVariable String taskId,
                                                                @RequestBody Map<String, Object> data,
                                                                Authentication authentication) {

        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();

        ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery()
                .processInstanceId(task.getProcessInstanceId())
                .activityId(task.getTaskDefinitionKey())
                .singleResult();

        execution.setTransientVariables(data);

        List<TaskDefinition> taskDefinitions = bpmActivityService
                .getNextActivities(task.getProcessDefinitionId(), task.getTaskDefinitionKey(), (execution));

        List<CandidateDetail> candidates = taskDefinitions.stream().map(TaskDefinition::getKey)
                .flatMap(key ->
                        processConfigurationService
                                .getActivityConfiguration(authentication.getUser().getId(), task.getProcessDefinitionId(), key)
                                .getCandidateInfo(task)
                                .stream())
                .distinct()
                .map(CandidateDetail::of)
                .collect(Collectors.toList());


        return ResponseMessage.ok(candidates);
    }
}
