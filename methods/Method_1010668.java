private void report(long time,String conceptFqName,Map<String,Pair<Long,Long>> map){
  if (!isEnabled)   return;
  Pair<Long,Long> value=map.get(conceptFqName);
  if (value == null) {
    value=new Pair<>(0L,0L);
    map.put(conceptFqName,value);
  }
  value.o1+=time;
  value.o2++;
}
