public static Map<String,List<BxTradeHistory>> prepareHistory(BxTradeHistory[] histories){
  Map<String,List<BxTradeHistory>> historyMap=new HashMap<>();
  for (  BxTradeHistory history : histories) {
    List<BxTradeHistory> list=historyMap.computeIfAbsent(String.valueOf(history.getRefId()),k -> new ArrayList<>());
    list.add(history);
  }
  for (  String key : historyMap.keySet()) {
    historyMap.get(key).sort(Comparator.comparingLong(BxTradeHistory::getTransactionId));
  }
  return historyMap;
}
