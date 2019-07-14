/** 
 * find out single activity's highlighted flowIds
 * @param activity
 * @param hisActInstList
 * @param isExclusive    if true only return one flowId(Such as exclusiveGateway, BoundaryEvent On Task)
 * @return
 */
private List<String> getHighlightedFlows(List<PvmTransition> pvmTransitionList,LinkedList<HistoricActivityInstance> hisActInstList,boolean isParallel){
  List<String> highLightedFlowIds=new ArrayList<String>();
  PvmTransition earliestTrans=null;
  HistoricActivityInstance earliestHisActInst=null;
  for (  PvmTransition pvmTransition : pvmTransitionList) {
    String destActId=pvmTransition.getDestination().getId();
    HistoricActivityInstance destHisActInst=findHisActInst(hisActInstList,destActId);
    if (destHisActInst != null) {
      if (isParallel) {
        highLightedFlowIds.add(pvmTransition.getId());
      }
 else       if (earliestHisActInst == null || (earliestHisActInst.getId().compareTo(destHisActInst.getId()) > 0)) {
        earliestTrans=pvmTransition;
        earliestHisActInst=destHisActInst;
      }
    }
  }
  if ((!isParallel) && earliestTrans != null) {
    highLightedFlowIds.add(earliestTrans.getId());
  }
  return highLightedFlowIds;
}
