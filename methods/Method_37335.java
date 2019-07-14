@Benchmark public Object smoothieMap(){
  final FastCharBuffer sb=new FastCharBuffer();
  for (  final int index : indexes) {
    sb.append(smoothieMap.get(TYPES[index]));
  }
  return sb;
}
