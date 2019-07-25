@Override public Iterator<Tuple2<Integer,Long>> call(Iterator<MessageAndMetadata<E>> it) throws Exception {
  MessageAndMetadata<E> mmeta=null;
  List<Tuple2<Integer,Long>> kafkaPartitionToOffsetList=new LinkedList<>();
  Map<Integer,Long> offsetMap=new HashMap<>();
  while (it.hasNext()) {
    mmeta=it.next();
    if (mmeta != null) {
      Long offset=offsetMap.get(mmeta.getPartition().partition);
      if (offset == null) {
        offsetMap.put(mmeta.getPartition().partition,mmeta.getOffset());
      }
 else {
        if (mmeta.getOffset() > offset) {
          offsetMap.put(mmeta.getPartition().partition,mmeta.getOffset());
        }
      }
    }
  }
  for (  Map.Entry<Integer,Long> entry : offsetMap.entrySet()) {
    kafkaPartitionToOffsetList.add(new Tuple2<>(entry.getKey(),entry.getValue()));
  }
  return kafkaPartitionToOffsetList.iterator();
}
