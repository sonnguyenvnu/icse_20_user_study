@Benchmark public Object weakMap(){
  final FastCharBuffer sb=new FastCharBuffer();
  for (  final int index : indexes) {
    sb.append(weakMap.get(TYPES[index]));
  }
  return sb;
}
