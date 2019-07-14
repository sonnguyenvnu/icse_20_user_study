@Benchmark public Object timedCache(){
  final FastCharBuffer sb=new FastCharBuffer();
  for (  final int index : indexes) {
    sb.append(timedCache.get(TYPES[index]));
  }
  return sb;
}
