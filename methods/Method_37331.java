@Benchmark public Object map(){
  final FastCharBuffer sb=new FastCharBuffer();
  for (  final int index : indexes) {
    sb.append(map.get(TYPES[index]));
  }
  return sb;
}
