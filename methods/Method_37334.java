@Benchmark public Object weakSyncMap(){
  final FastCharBuffer sb=new FastCharBuffer();
  for (  final int index : indexes) {
    sb.append(weakSyncMap.get(TYPES[index]));
  }
  return sb;
}
