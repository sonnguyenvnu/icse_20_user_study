public TreeSet<String> labelSet(){
  TreeSet<String> labelSet=new TreeSet<String>();
  for (  Map.Entry<String,Item> entry : entrySet()) {
    labelSet.addAll(entry.getValue().labelMap.keySet());
  }
  return labelSet;
}
