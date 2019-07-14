/** 
 * getHighlightedFlows <p> code logic: 1. Loop all activities by id asc order; 2. Check each activity's outgoing transitions and eventBoundery outgoing transitions, if outgoing transitions's destination.id is in other executed activityIds, add this transition to highLightedFlows List; 3. But if activity is not a parallelGateway or inclusiveGateway, only choose the earliest flow.
 * @param activityList
 * @param hisActInstList
 * @param highLightedFlows
 */
private void getHighlightedFlows(List<ActivityImpl> activityList,LinkedList<HistoricActivityInstance> hisActInstList,List<String> highLightedFlows){
  List<ActivityImpl> startEventActList=new ArrayList<ActivityImpl>();
  Map<String,ActivityImpl> activityMap=new HashMap<String,ActivityImpl>(activityList.size());
  for (  ActivityImpl activity : activityList) {
    activityMap.put(activity.getId(),activity);
    String actType=(String)activity.getProperty("type");
    if (actType != null && actType.toLowerCase().indexOf("startevent") >= 0) {
      startEventActList.add(activity);
    }
  }
  HistoricActivityInstance firstHistActInst=hisActInstList.getFirst();
  String firstActType=(String)firstHistActInst.getActivityType();
  if (firstActType != null && firstActType.toLowerCase().indexOf("startevent") < 0) {
    PvmTransition startTrans=getStartTransaction(startEventActList,firstHistActInst);
    if (startTrans != null) {
      highLightedFlows.add(startTrans.getId());
    }
  }
  while (!hisActInstList.isEmpty()) {
    HistoricActivityInstance histActInst=hisActInstList.removeFirst();
    ActivityImpl activity=activityMap.get(histActInst.getActivityId());
    if (activity != null) {
      boolean isParallel=false;
      String type=histActInst.getActivityType();
      if ("parallelGateway".equals(type) || "inclusiveGateway".equals(type)) {
        isParallel=true;
      }
 else       if ("subProcess".equals(histActInst.getActivityType())) {
        getHighlightedFlows(activity.getActivities(),hisActInstList,highLightedFlows);
      }
      List<PvmTransition> allOutgoingTrans=new ArrayList<PvmTransition>();
      allOutgoingTrans.addAll(activity.getOutgoingTransitions());
      allOutgoingTrans.addAll(getBoundaryEventOutgoingTransitions(activity));
      List<String> activityHighLightedFlowIds=getHighlightedFlows(allOutgoingTrans,hisActInstList,isParallel);
      highLightedFlows.addAll(activityHighLightedFlowIds);
    }
  }
}
