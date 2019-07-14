private HistoricActivityInstance findHisActInst(LinkedList<HistoricActivityInstance> hisActInstList,String actId){
  for (  HistoricActivityInstance hisActInst : hisActInstList) {
    if (hisActInst.getActivityId().equals(actId)) {
      return hisActInst;
    }
  }
  return null;
}
