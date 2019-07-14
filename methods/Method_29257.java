@Override public Map<String,String> getClusterLossSlots(long appId){
  InstanceInfo sourceMasterInstance=getHealthyInstanceInfo(appId);
  if (sourceMasterInstance == null) {
    return Collections.emptyMap();
  }
  Map<Integer,String> slotHostPortMap=getSlotsHostPortMap(appId,sourceMasterInstance.getIp(),sourceMasterInstance.getPort());
  List<Integer> lossSlotList=getClusterLossSlots(appId,sourceMasterInstance.getIp(),sourceMasterInstance.getPort());
  Map<String,List<Integer>> hostPortSlotMap=new HashMap<String,List<Integer>>();
  if (CollectionUtils.isNotEmpty(lossSlotList)) {
    for (    Integer lossSlot : lossSlotList) {
      String key=slotHostPortMap.get(lossSlot);
      if (hostPortSlotMap.containsKey(key)) {
        hostPortSlotMap.get(key).add(lossSlot);
      }
 else {
        List<Integer> list=new ArrayList<Integer>();
        list.add(lossSlot);
        hostPortSlotMap.put(key,list);
      }
    }
  }
  Map<String,String> slotSegmentsMap=new HashMap<String,String>();
  for (  Entry<String,List<Integer>> entry : hostPortSlotMap.entrySet()) {
    List<Integer> list=entry.getValue();
    List<String> slotSegments=new ArrayList<String>();
    int min=list.get(0);
    int max=min;
    for (int i=1; i < list.size(); i++) {
      int temp=list.get(i);
      if (temp == max + 1) {
        max=temp;
      }
 else {
        slotSegments.add(String.valueOf(min) + "-" + String.valueOf(max));
        min=temp;
        max=temp;
      }
    }
    slotSegments.add(String.valueOf(min) + "-" + String.valueOf(max));
    slotSegmentsMap.put(entry.getKey(),slotSegments.toString());
  }
  return slotSegmentsMap;
}
