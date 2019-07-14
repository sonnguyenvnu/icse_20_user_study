/** 
 * Check out the outgoing transition connected to firstActInst from startEventActList
 * @param startEventActList
 * @param firstActInst
 * @return
 */
private PvmTransition getStartTransaction(List<ActivityImpl> startEventActList,HistoricActivityInstance firstActInst){
  for (  ActivityImpl startEventAct : startEventActList) {
    for (    PvmTransition trans : startEventAct.getOutgoingTransitions()) {
      if (trans.getDestination().getId().equals(firstActInst.getActivityId())) {
        return trans;
      }
    }
  }
  return null;
}
