@Benchmark public Object simpleHashMap(){
  final FastCharBuffer sb=new FastCharBuffer();
  for (  final int index : indexes) {
    sb.append(simpleHashMap.get(TYPES[index]));
  }
  return sb;
}
