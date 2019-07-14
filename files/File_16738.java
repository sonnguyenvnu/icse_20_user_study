package org.hswebframework.web.workflow.service.imp;

import lombok.SneakyThrows;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.hswebframework.ezorm.rdb.executor.SqlExecutor;
import org.hswebframework.utils.StringUtils;
import org.hswebframework.web.BusinessException;
import org.hswebframework.web.Maps;
import org.hswebframework.web.NotFoundException;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.commons.entity.PagerResult;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.workflow.dao.entity.ProcessHistoryEntity;
import org.hswebframework.web.workflow.service.ProcessHistoryService;
import org.hswebframework.web.workflow.service.config.ProcessConfigurationService;
import org.hswebframework.web.workflow.service.BpmActivityService;
import org.hswebframework.web.workflow.service.BpmTaskService;
import org.hswebframework.web.workflow.flowable.utils.JumpTaskCmd;
import org.hswebframework.web.workflow.service.WorkFlowFormService;
import org.hswebframework.web.workflow.service.config.CandidateInfo;
import org.hswebframework.web.workflow.service.request.CompleteTaskRequest;
import org.hswebframework.web.workflow.service.request.JumpTaskRequest;
import org.hswebframework.web.workflow.service.request.RejectTaskRequest;
import org.hswebframework.web.workflow.service.request.SaveFormRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/**
 * @author wangwei
 * @author zhouhao
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BpmTaskServiceImpl extends AbstractFlowableService implements BpmTaskService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BpmActivityService bpmActivityService;

    @Autowired
    private ProcessConfigurationService processConfigurationService;

    @Autowired
    private WorkFlowFormService workFlowFormService;

    @Autowired
    private ProcessHistoryService processHistoryService;

    @Autowired
    private SqlExecutor sqlExecutor;

    @Override
    public List<Task> selectNowTask(String procInstId) {
        return taskService.createTaskQuery()
                .processInstanceId(procInstId)
                .active()
                .list();
    }

    @Override
    public List<Task> selectTaskByProcessId(String procInstId) {
        return taskService
                .createTaskQuery()
                .processInstanceId(procInstId)
                .active()
                .list();
    }

    @Override
    public Task selectTaskByTaskId(String taskId) {
        return taskService
                .createTaskQuery()
                .taskId(taskId)
                .active()
                .singleResult();
    }

    @Override
    public void claim(String taskId, String userId) {
        Task task = taskService.createTaskQuery().
                taskId(taskId)
                .taskCandidateUser(userId)
                .active()
                .singleResult();
        if (task == null) {
            throw new NotFoundException("无法签收此任务");
        }
        if (!StringUtils.isNullOrEmpty(task.getAssignee())) {
            throw new BusinessException("任务已签售");
        } else {
            taskService.claim(taskId, userId);
        }
    }

    @Override
    public void complete(CompleteTaskRequest request) {
        request.tryValidate();

        Task task = taskService.createTaskQuery()
                .taskId(request.getTaskId())
                .includeProcessVariables()
                .active()
                .singleResult();

        Objects.requireNonNull(task, "任务�?存在");
        String assignee = task.getAssignee();
        Objects.requireNonNull(assignee, "任务未签收");
        if (!assignee.equals(request.getCompleteUserId())) {
            throw new BusinessException("�?�能完�?自己的任务");
        }
        Map<String, Object> variable = new HashMap<>();
        variable.put("preTaskId", task.getId());
        Map<String, Object> transientVariables = new HashMap<>();

        if (null != request.getVariables()) {
            variable.putAll(request.getVariables());
            transientVariables.putAll(request.getVariables());
        }

        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();

        //查询主表的数�?�作为�?��?
        Optional.of(workFlowFormService.<Map<String, Object>>selectProcessForm(instance.getProcessDefinitionId(),
                QueryParamEntity.of("processInstanceId", instance.getProcessInstanceId()).doPaging(0, 2)))
                .map(PagerResult::getData)
                .map(list -> {
                    if (list.size() == 1) {
                        return list.get(0);
                    }
                    if (list.size() > 1) {
                        logger.warn("主表数�?�存在多�?�数�?�:processInstanceId={}", instance.getProcessInstanceId());
                    }
                    return null;
                })
                .ifPresent(transientVariables::putAll);


        //�?存表�?�数�?�
        workFlowFormService.saveTaskForm(instance, task, SaveFormRequest.builder()
                .userName(request.getCompleteUserName())
                .userId(request.getCompleteUserId())
                .formData(request.getFormData())
                .build());

        if (null != request.getFormData()) {
            transientVariables.putAll(request.getFormData());
        }

        taskService.complete(task.getId(), null, transientVariables);

        //跳转
        if (!StringUtils.isNullOrEmpty(request.getNextActivityId())) {
            doJumpTask(task, request.getNextActivityId(), (t) -> {
            });
        }

        //下一步候选人
        List<Task> tasks = selectNowTask(task.getProcessInstanceId());
        for (Task next : tasks) {
            setVariablesLocal(next.getId(), variable);
            if (!StringUtils.isNullOrEmpty(request.getNextClaimUserId())) {
                taskService.addCandidateUser(next.getId(), request.getNextClaimUserId());
            } else {
                setCandidate(request.getCompleteUserId(), next);
            }
        }

        ProcessHistoryEntity history = ProcessHistoryEntity.builder()
                .businessKey(instance.getBusinessKey())
                .type("complete")
                .typeText("完�?任务")
                .creatorId(request.getCompleteUserId())
                .creatorName(request.getCompleteUserName())
                .processDefineId(instance.getProcessDefinitionId())
                .processInstanceId(instance.getProcessInstanceId())
                .taskId(task.getId())
                .taskDefineKey(task.getTaskDefinitionKey())
                .taskName(task.getName())
                .build();

        processHistoryService.insert(history);
    }

    @Override
    @SneakyThrows
    public void reject(RejectTaskRequest request) {
        request.tryValidate();
        String taskId = request.getTaskId();
        Task curTask = selectTaskByTaskId(taskId);
        if (curTask == null) {
            throw new NotFoundException("任务�?存在或未激活");
        }
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(curTask.getProcessInstanceId())
                .singleResult();

        ProcessDefinitionEntity entity = (ProcessDefinitionEntity)
                ((RepositoryServiceImpl) repositoryService)
                        .getDeployedProcessDefinition(curTask.getProcessDefinitionId());

        ActivityImpl currActivity = entity.findActivity(curTask.getTaskDefinitionKey());

        List<PvmActivity> transitions = new ArrayList<>();
        //查找上一个环节
        findActivity(currActivity,
                activity ->
                        activity.getIncomingTransitions()
                                .stream()
                                .map(PvmTransition::getSource)
                                .collect(Collectors.toList()),
                activity -> transitions.isEmpty() && "userTask".equals(activity.getProperty("type")),
                transitions::add);

        if (!transitions.isEmpty()) {
            //跳转到上一环节
            PvmActivity transition = transitions.get(transitions.size() - 1);
            doJumpTask(curTask, transition.getId(), newTask -> {
            });
        } else {
            throw new BusinessException("无法获�?�上一步任务");
        }

        //记录日志
        ProcessHistoryEntity history = ProcessHistoryEntity.builder()
                .businessKey(processInstance.getBusinessKey())
                .type("reject")
                .typeText("驳回")
                .creatorId(request.getRejectUserId())
                .creatorName(request.getRejectUserName())
                .processDefineId(processInstance.getProcessDefinitionId())
                .processInstanceId(processInstance.getProcessInstanceId())
                .taskId(taskId)
                .taskDefineKey(curTask.getTaskDefinitionKey())
                .taskName(curTask.getName())
                .data(request.getData())
                .build();

        processHistoryService.insert(history);
    }

    protected void findActivity(
            PvmActivity activity,
            Function<PvmActivity, List<PvmActivity>> function,
            Predicate<PvmActivity> predicate,
            Consumer<PvmActivity> consumer) {

        List<PvmActivity> activities = function.apply(activity);
        for (PvmActivity pvmActivity : activities) {
            consumer.accept(pvmActivity);
            if (predicate.test(pvmActivity)) {
                return;
            }
            //往下查找
            findActivity(pvmActivity, function, predicate, consumer);
        }
    }

    @SneakyThrows
    public void doJumpTask(Task task, String activityId, Consumer<Task> newTaskConsumer) {

        ProcessDefinitionEntity entity = (ProcessDefinitionEntity)
                ((RepositoryServiceImpl) repositoryService)
                        .getDeployedProcessDefinition(task.getProcessDefinitionId());

        String sourceId = task.getTaskDefinitionKey();

        ActivityImpl targetActivity = entity.findActivity(activityId);
        ActivityImpl sourceActivity = entity.findActivity(sourceId);

        if (logger.isDebugEnabled()) {
            logger.debug("�?程[{}({})]跳转[{}]->[{}]",
                    entity.getName(),
                    entity.getId(),
                    sourceActivity.getId(),
                    targetActivity.getId());
        }

        //回退的节点
        List<PvmActivity> backActivities = new ArrayList<>();
        //如果目标节点的Outgoing中有�?节点,说明是回退需�?删除对应的连线
        findActivity(targetActivity,
                activity -> activity
                        .getOutgoingTransitions()
                        .stream()
                        .map(PvmTransition::getDestination)
                        .collect(Collectors.toList()),
                activity -> sourceActivity.getId().equals(activity.getId()),
                backActivities::add);

        //回退
        if (!backActivities.isEmpty()) {
            for (PvmActivity pvmTransition : backActivities) {
                if (logger.isDebugEnabled()) {
                    logger.debug("�?程[{}({})]回退[{}]->[{}],删除链接线:{}",
                            entity.getName(),
                            entity.getId(),
                            sourceActivity.getId(),
                            targetActivity.getId(),
                            pvmTransition.getId());
                }
                //删除连线
                List<HistoricActivityInstance> instance = historyService
                        .createHistoricActivityInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId())
                        .activityId(pvmTransition.getId())
                        .list();
                for (HistoricActivityInstance historicActivityInstance : instance) {
                    sqlExecutor.delete("delete from act_hi_actinst where id_= #{id}", historicActivityInstance);
                }
            }
        }
        //执行回退命令
        TaskServiceImpl taskServiceImpl = (TaskServiceImpl) taskService;
        taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(task.getExecutionId(), activityId));
        //设置候选人并回调
        selectNowTask(task.getProcessInstanceId())
                .forEach(t -> {
                    //设置候选人
                    setCandidate(task.getAssignee(), t);
                    newTaskConsumer.accept(t);
                });

    }

    @Override
    public void jumpTask(JumpTaskRequest request) {
        request.tryValidate();

        Task task = taskService.createTaskQuery()
                .taskId(request.getTaskId())
                .singleResult();

        //记录跳转�?�的节点到日志
        List<Map<String, String>> targetTask = new ArrayList<>();

        doJumpTask(task, request.getTargetActivityId(), t -> {
            Map<String, String> target = new HashMap<>();
            target.put("taskId", t.getId());
            target.put("taskName", t.getName());
            target.put("activityId", t.getTaskDefinitionKey());
            targetTask.add(target);
        });

        if (request.isRecordLog()) {

            ProcessInstance processInstance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .singleResult();

            Map<String, Object> data = new HashMap<>();
            data.put("targetTask", targetTask);
            if (request.getData() != null) {
                data.putAll(request.getData());
            }

            ProcessHistoryEntity history = ProcessHistoryEntity.builder()
                    .businessKey(processInstance.getBusinessKey())
                    .type("jump")
                    .typeText("�?程跳转")
                    .creatorId(request.getJumpUserId())
                    .creatorName(request.getJumpUserName())
                    .processDefineId(processInstance.getProcessDefinitionId())
                    .processInstanceId(processInstance.getProcessInstanceId())
                    .taskId(task.getId())
                    .taskDefineKey(task.getTaskDefinitionKey())
                    .taskName(task.getName())
                    .data(data)
                    .build();

            processHistoryService.insert(history);
        }
    }

    @Override
    public void endProcess(String procInstId) {
//        ProcessInstance processInstance = runtimeService
//                .createProcessInstanceQuery()
//                .processInstanceId(procInstId)
//                .singleResult();
//        ActivityImpl activity = bpmActivityService.getEndEvent(processInstance.getProcessDefinitionId());
//        List<Task> tasks = selectNowTask(procInstId);
//
//        if (!tasks.isEmpty()) {
//            doJumpTask(tasks.get(0).getId(), activity.getId());
//        }

    }

    @Override
    public void removeHiTask(String taskId) {
        historyService.deleteHistoricTaskInstance(taskId);
    }

    @Override
    public Map<String, Object> selectVariableLocalByTaskId(String taskId) {
        return taskService.getVariablesLocal(taskId);
    }

    @Override
    public String selectVariableLocalByTaskId(String taskId, String variableName) {
        return (String) taskService.getVariableLocal(taskId, variableName);
    }

    @Override
    public HistoricProcessInstance selectHisProInst(String procInstId) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult();
    }

    @Override
    public void setCandidate(String doingUserId, Task task) {
        if (task == null) {
            return;
        }
        if (task.getTaskDefinitionKey() != null) {
            //从�?置中获�?�候选人
            List<CandidateInfo> candidateInfoList = processConfigurationService
                    .getActivityConfiguration(doingUserId, task.getProcessDefinitionId(), task.getTaskDefinitionKey())
                    .getCandidateInfo(task);
            if (CollectionUtils.isEmpty(candidateInfoList)) {
                logger.warn("任务:{}未能设置候选人,此任务�?�能无法办�?�!", task);
            } else {
                for (CandidateInfo candidateInfo : candidateInfoList) {
                    Authentication user = candidateInfo.user();
                    if (user != null) {
                        taskService.addCandidateUser(task.getId(), user.getUser().getId());
                    }
                }
            }
        } else {
            logger.warn("未能�?功设置环节候选人,task:{}", task);
        }
    }

    @Override
    public ActivityImpl selectActivityImplByTask(String taskId) {
        if (StringUtils.isNullOrEmpty(taskId)) {
            return new ActivityImpl(null, null);
        }
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
        List<ActivityImpl> activities = entity.getActivities();
        return activities
                .stream()
                .filter(activity -> "userTask".equals(activity.getProperty("type")) && activity.getProperty("name").equals(task.getName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("获�?�节点信�?�失败"));
    }

    @Override
    public Map<String, Object> getUserTasksByProcDefKey(String procDefKey) {
        String definitionId = repositoryService.createProcessDefinitionQuery().processDefinitionKey(procDefKey).orderByProcessDefinitionVersion().desc().list().get(0).getId();
        List<ActivityImpl> activities = bpmActivityService.getUserTasksByProcDefId(definitionId);
        Map<String, Object> map = new HashMap<>();
        for (ActivityImpl activity : activities) {
            map.put(activity.getId(), activity.getProperty("name"));
        }
        return map;
    }

    @Override
    public Map<String, Object> getUserTasksByProcInstId(String procInstId) {
        String definitionId = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult().getProcessDefinitionId();
        List<ActivityImpl> activities = bpmActivityService.getUserTasksByProcDefId(definitionId);
        Map<String, Object> map = new HashMap<>();
        for (ActivityImpl activity : activities) {
            map.put(activity.getId(), activity.getProperty("name"));
        }
        return map;
    }

    @Override
    public void setVariables(String taskId, Map<String, Object> map) {
        taskService.setVariables(taskId, map);
    }

    @Override
    public void removeVariables(String taskId, Collection<String> var2) {
        taskService.removeVariables(taskId, var2);
    }

    @Override
    public void setVariablesLocal(String taskId, Map<String, Object> map) {
        taskService.setVariablesLocal(taskId, map);
    }

    @Override
    public Map<String, Object> getVariablesByProcInstId(String procInstId) {
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(procInstId).list();
        String executionId = "";
        for (Execution execution : executions) {
            if (StringUtils.isNullOrEmpty(execution.getParentId())) {
                executionId = execution.getId();
            }
        }
        return runtimeService.getVariables(executionId);
    }

    @Override
    public Map<String, Object> getVariablesByTaskId(String taskId) {
        return taskService.getVariables(taskId);
    }
}
