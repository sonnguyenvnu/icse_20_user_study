private static int getPercentValue(Long totalSize,Map<Integer,Long> sortKeyMap,double percent){
  Long targetLocation=(long)(totalSize * percent / 100.0);
  Long count=0L;
  Integer key=0;
  for (  Entry<Integer,Long> entry : sortKeyMap.entrySet()) {
    key=entry.getKey();
    count+=entry.getValue();
    if (count > targetLocation) {
      break;
    }
  }
  return key;
}
