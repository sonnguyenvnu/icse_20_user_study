@Benchmark public Object syncMap(){
  final FastCharBuffer sb=new FastCharBuffer();
  for (  final int index : indexes) {
    sb.append(syncMap.get(TYPES[index]));
  }
  return sb;
}
