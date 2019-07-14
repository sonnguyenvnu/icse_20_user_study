private void getActivity(String processInstanceId,ActivityImpl activity,List<Object> activityArray,List<Object> sequenceFlowArray,ProcessInstance processInstance,List<String> highLightedFlows,Map<String,Object> subProcessInstanceMap){
  JSONObject activityJSON=new JSONObject();
  String multiInstance=(String)activity.getProperty("multiInstance");
  if (multiInstance != null) {
    if (!"sequential".equals(multiInstance)) {
      multiInstance="parallel";
    }
  }
  ActivityBehavior activityBehavior=activity.getActivityBehavior();
  Boolean collapsed=(activityBehavior instanceof CallActivityBehavior);
  Boolean expanded=(Boolean)activity.getProperty(BpmnParse.PROPERTYNAME_ISEXPANDED);
  if (expanded != null) {
    collapsed=!expanded;
  }
  Boolean isInterrupting=null;
  if (activityBehavior instanceof BoundaryEventActivityBehavior) {
    isInterrupting=((BoundaryEventActivityBehavior)activityBehavior).isInterrupting();
  }
  for (  PvmTransition sequenceFlow : activity.getOutgoingTransitions()) {
    String flowName=(String)sequenceFlow.getProperty("name");
    boolean isHighLighted=(highLightedFlows.contains(sequenceFlow.getId()));
    boolean isConditional=sequenceFlow.getProperty(BpmnParse.PROPERTYNAME_CONDITION) != null && !((String)activity.getProperty("type")).toLowerCase().contains("gateway");
    boolean isDefault=sequenceFlow.getId().equals(activity.getProperty("default")) && ((String)activity.getProperty("type")).toLowerCase().contains("gateway");
    List<Integer> waypoints=((TransitionImpl)sequenceFlow).getWaypoints();
    JSONArray xPointArray=new JSONArray();
    JSONArray yPointArray=new JSONArray();
    for (int i=0; i < waypoints.size(); i+=2) {
      xPointArray.add(waypoints.get(i));
      yPointArray.add(waypoints.get(i + 1));
    }
    JSONObject flowJSON=new JSONObject();
    flowJSON.put("id",sequenceFlow.getId());
    flowJSON.put("name",flowName);
    flowJSON.put("flow","(" + sequenceFlow.getSource().getId() + ")--" + sequenceFlow.getId() + "-->(" + sequenceFlow.getDestination().getId() + ")");
    if (isConditional)     flowJSON.put("isConditional",isConditional);
    if (isDefault)     flowJSON.put("isDefault",isDefault);
    if (isHighLighted)     flowJSON.put("isHighLighted",isHighLighted);
    flowJSON.put("xPointArray",xPointArray);
    flowJSON.put("yPointArray",yPointArray);
    sequenceFlowArray.add(flowJSON);
  }
  JSONArray nestedActivityArray=new JSONArray();
  for (  ActivityImpl nestedActivity : activity.getActivities()) {
    nestedActivityArray.add(nestedActivity.getId());
  }
  Map<String,Object> properties=activity.getProperties();
  JSONObject propertiesJSON=new JSONObject();
  for (  String key : properties.keySet()) {
    Object prop=properties.get(key);
    if (prop instanceof String)     propertiesJSON.put(key,properties.get(key));
 else     if (prop instanceof Integer)     propertiesJSON.put(key,properties.get(key));
 else     if (prop instanceof Boolean)     propertiesJSON.put(key,properties.get(key));
 else     if ("initial".equals(key)) {
      ActivityImpl act=(ActivityImpl)properties.get(key);
      propertiesJSON.put(key,act.getId());
    }
 else     if ("timerDeclarations".equals(key)) {
      ArrayList<TimerDeclarationImpl> timerDeclarations=(ArrayList<TimerDeclarationImpl>)properties.get(key);
      JSONArray timerDeclarationArray=new JSONArray();
      if (timerDeclarations != null)       for (      TimerDeclarationImpl timerDeclaration : timerDeclarations) {
        JSONObject timerDeclarationJSON=new JSONObject();
        timerDeclarationJSON.put("isExclusive",timerDeclaration.isExclusive());
        if (timerDeclaration.getRepeat() != null)         timerDeclarationJSON.put("repeat",timerDeclaration.getRepeat());
        timerDeclarationJSON.put("retries",String.valueOf(timerDeclaration.getRetries()));
        timerDeclarationJSON.put("type",timerDeclaration.getJobHandlerType());
        timerDeclarationJSON.put("configuration",timerDeclaration.getJobHandlerConfiguration());
        timerDeclarationArray.add(timerDeclarationJSON);
      }
      if (timerDeclarationArray.size() > 0)       propertiesJSON.put(key,timerDeclarationArray);
    }
 else     if ("eventDefinitions".equals(key)) {
      ArrayList<EventSubscriptionDeclaration> eventDefinitions=(ArrayList<EventSubscriptionDeclaration>)properties.get(key);
      JSONArray eventDefinitionsArray=new JSONArray();
      if (eventDefinitions != null) {
        for (        EventSubscriptionDeclaration eventDefinition : eventDefinitions) {
          JSONObject eventDefinitionJSON=new JSONObject();
          if (eventDefinition.getActivityId() != null)           eventDefinitionJSON.put("activityId",eventDefinition.getActivityId());
          eventDefinitionJSON.put("eventName",eventDefinition.getEventName());
          eventDefinitionJSON.put("eventType",eventDefinition.getEventType());
          eventDefinitionJSON.put("isAsync",eventDefinition.isAsync());
          eventDefinitionJSON.put("isStartEvent",eventDefinition.isStartEvent());
          eventDefinitionsArray.add(eventDefinitionJSON);
        }
      }
      if (eventDefinitionsArray.size() > 0)       propertiesJSON.put(key,eventDefinitionsArray);
    }
 else     if ("errorEventDefinitions".equals(key)) {
      ArrayList<ErrorEventDefinition> errorEventDefinitions=(ArrayList<ErrorEventDefinition>)properties.get(key);
      JSONArray errorEventDefinitionsArray=new JSONArray();
      if (errorEventDefinitions != null) {
        for (        ErrorEventDefinition errorEventDefinition : errorEventDefinitions) {
          JSONObject errorEventDefinitionJSON=new JSONObject();
          if (errorEventDefinition.getErrorCode() != null)           errorEventDefinitionJSON.put("errorCode",errorEventDefinition.getErrorCode());
 else           errorEventDefinitionJSON.put("errorCode",null);
          errorEventDefinitionJSON.put("handlerActivityId",errorEventDefinition.getHandlerActivityId());
          errorEventDefinitionsArray.add(errorEventDefinitionJSON);
        }
      }
      if (errorEventDefinitionsArray.size() > 0)       propertiesJSON.put(key,errorEventDefinitionsArray);
    }
  }
  if ("callActivity".equals(properties.get("type"))) {
    CallActivityBehavior callActivityBehavior=null;
    if (activityBehavior instanceof CallActivityBehavior) {
      callActivityBehavior=(CallActivityBehavior)activityBehavior;
    }
    if (callActivityBehavior != null) {
      propertiesJSON.put("processDefinitonKey",callActivityBehavior.getProcessDefinitonKey());
      JSONArray processInstanceArray=new JSONArray();
      if (processInstance != null) {
        List<Execution> executionList=runtimeService.createExecutionQuery().processInstanceId(processInstanceId).activityId(activity.getId()).list();
        if (!executionList.isEmpty()) {
          for (          Execution execution : executionList) {
            processInstanceArray.add(subProcessInstanceMap.get(execution.getId()));
          }
        }
      }
      if (processInstanceArray.size() == 0 && StringUtils.isNotEmpty(callActivityBehavior.getProcessDefinitonKey())) {
        ProcessDefinition lastProcessDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey(callActivityBehavior.getProcessDefinitonKey()).latestVersion().singleResult();
        if (lastProcessDefinition != null) {
          JSONObject processInstanceJSON=new JSONObject();
          processInstanceJSON.put("processDefinitionId",lastProcessDefinition.getId());
          processInstanceJSON.put("processDefinitionKey",lastProcessDefinition.getKey());
          processInstanceJSON.put("processDefinitionName",lastProcessDefinition.getName());
          processInstanceArray.add(processInstanceJSON);
        }
      }
      if (processInstanceArray.size() > 0) {
        propertiesJSON.put("processDefinitons",processInstanceArray);
      }
    }
  }
  activityJSON.put("activityId",activity.getId());
  activityJSON.put("properties",propertiesJSON);
  if (multiInstance != null)   activityJSON.put("multiInstance",multiInstance);
  if (collapsed)   activityJSON.put("collapsed",collapsed);
  if (nestedActivityArray.size() > 0)   activityJSON.put("nestedActivities",nestedActivityArray);
  if (isInterrupting != null)   activityJSON.put("isInterrupting",isInterrupting);
  activityJSON.put("x",activity.getX());
  activityJSON.put("y",activity.getY());
  activityJSON.put("width",activity.getWidth());
  activityJSON.put("height",activity.getHeight());
  activityArray.add(activityJSON);
  for (  ActivityImpl nestedActivity : activity.getActivities()) {
    getActivity(processInstanceId,nestedActivity,activityArray,sequenceFlowArray,processInstance,highLightedFlows,subProcessInstanceMap);
  }
}
