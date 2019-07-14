public Map<String,Object> getDiagramNode(String processInstanceId,String processDefinitionId){
  List<String> highLightedFlows=Collections.<String>emptyList();
  List<String> highLightedActivities;
  Map<String,Object> subProcessInstanceMap=new HashMap<>();
  ProcessInstance processInstance=null;
  if (processInstanceId != null) {
    processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    if (processInstance == null) {
      throw new ActivitiObjectNotFoundException("Process instance could not be found");
    }
    processDefinitionId=processInstance.getProcessDefinitionId();
    List<ProcessInstance> subProcessInstances=runtimeService.createProcessInstanceQuery().superProcessInstanceId(processInstanceId).list();
    for (    ProcessInstance subProcessInstance : subProcessInstances) {
      String subDefId=subProcessInstance.getProcessDefinitionId();
      String superExecutionId=(subProcessInstance).getSuperExecutionId();
      ProcessDefinitionEntity subDef=(ProcessDefinitionEntity)repositoryService.getProcessDefinition(subDefId);
      JSONObject processInstanceJSON=new JSONObject();
      processInstanceJSON.put("processInstanceId",subProcessInstance.getId());
      processInstanceJSON.put("superExecutionId",superExecutionId);
      processInstanceJSON.put("processDefinitionId",subDef.getId());
      processInstanceJSON.put("processDefinitionKey",subDef.getKey());
      processInstanceJSON.put("processDefinitionName",subDef.getName());
      subProcessInstanceMap.put(superExecutionId,processInstanceJSON);
    }
  }
  if (processDefinitionId == null) {
    throw new ActivitiObjectNotFoundException("No process definition id provided");
  }
  ProcessDefinitionEntity processDefinition=(ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
  if (processDefinition == null) {
    throw new ActivitiException("Process definition " + processDefinitionId + " could not be found");
  }
  JSONObject responseJSON=new JSONObject();
  Map<String,Object> pdrJSON=getProcessDefinitionResponse(processDefinition);
  if (pdrJSON != null) {
    responseJSON.put("processDefinition",pdrJSON);
  }
  if (processInstance != null) {
    JSONArray activityArray=new JSONArray();
    JSONArray flowsArray=new JSONArray();
    highLightedActivities=runtimeService.getActiveActivityIds(processInstanceId);
    highLightedFlows=getHighLightedFlows(processInstanceId,processDefinition);
    for (    String activityName : highLightedActivities) {
      activityArray.add(activityName);
    }
    for (    String flow : highLightedFlows)     flowsArray.add(flow);
    responseJSON.put("highLightedActivities",activityArray);
    responseJSON.put("highLightedFlows",flowsArray);
  }
  if (processDefinition.getParticipantProcess() != null) {
    ParticipantProcess pProc=processDefinition.getParticipantProcess();
    JSONObject participantProcessJSON=new JSONObject();
    participantProcessJSON.put("id",pProc.getId());
    if (StringUtils.isNotEmpty(pProc.getName())) {
      participantProcessJSON.put("name",pProc.getName());
    }
 else {
      participantProcessJSON.put("name","");
    }
    participantProcessJSON.put("x",pProc.getX());
    participantProcessJSON.put("y",pProc.getY());
    participantProcessJSON.put("width",pProc.getWidth());
    participantProcessJSON.put("height",pProc.getHeight());
    responseJSON.put("participantProcess",participantProcessJSON);
  }
  if (processDefinition.getLaneSets() != null && !processDefinition.getLaneSets().isEmpty()) {
    JSONArray laneSetArray=new JSONArray();
    for (    LaneSet laneSet : processDefinition.getLaneSets()) {
      JSONArray laneArray=new JSONArray();
      if (laneSet.getLanes() != null && !laneSet.getLanes().isEmpty()) {
        for (        Lane lane : laneSet.getLanes()) {
          JSONObject laneJSON=new JSONObject();
          laneJSON.put("id",lane.getId());
          if (StringUtils.isNotEmpty(lane.getName())) {
            laneJSON.put("name",lane.getName());
          }
 else {
            laneJSON.put("name","");
          }
          laneJSON.put("x",lane.getX());
          laneJSON.put("y",lane.getY());
          laneJSON.put("width",lane.getWidth());
          laneJSON.put("height",lane.getHeight());
          List<String> flowNodeIds=lane.getFlowNodeIds();
          JSONArray flowNodeIdsArray=new JSONArray();
          for (          String flowNodeId : flowNodeIds) {
            flowNodeIdsArray.add(flowNodeId);
          }
          laneJSON.put("flowNodeIds",flowNodeIdsArray);
          laneArray.add(laneJSON);
        }
      }
      JSONObject laneSetJSON=new JSONObject();
      laneSetJSON.put("id",laneSet.getId());
      if (StringUtils.isNotEmpty(laneSet.getName())) {
        laneSetJSON.put("name",laneSet.getName());
      }
 else {
        laneSetJSON.put("name","");
      }
      laneSetJSON.put("lanes",laneArray);
      laneSetArray.add(laneSetJSON);
    }
    if (laneSetArray.size() > 0)     responseJSON.put("laneSets",laneSetArray);
  }
  JSONArray sequenceFlowArray=new JSONArray();
  JSONArray activityArray=new JSONArray();
  for (  ActivityImpl activity : processDefinition.getActivities()) {
    getActivity(processInstanceId,activity,activityArray,sequenceFlowArray,processInstance,highLightedFlows,subProcessInstanceMap);
  }
  responseJSON.put("activities",activityArray);
  responseJSON.put("sequenceFlows",sequenceFlowArray);
  return responseJSON;
}
