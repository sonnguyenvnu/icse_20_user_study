@Override public String toString(){
  Set<String> labelSet=new TreeSet<String>();
  for (  Map.Entry<String,Map<String,Integer>> first : transferMatrix.entrySet()) {
    labelSet.add(first.getKey());
    labelSet.addAll(first.getValue().keySet());
  }
  final StringBuilder sb=new StringBuilder();
  sb.append(' ');
  for (  String key : labelSet) {
    sb.append(',');
    sb.append(key);
  }
  sb.append('\n');
  for (  String first : labelSet) {
    Map<String,Integer> firstMatrix=transferMatrix.get(first);
    if (firstMatrix == null)     firstMatrix=new TreeMap<String,Integer>();
    sb.append(first);
    for (    String second : labelSet) {
      sb.append(',');
      Integer frequency=firstMatrix.get(second);
      if (frequency == null)       frequency=0;
      sb.append(frequency);
    }
    sb.append('\n');
  }
  return sb.toString();
}
