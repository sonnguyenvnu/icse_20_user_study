static void combineReverseChain(TreeMap<String,String> t2s,TreeMap<String,String> tw2t,boolean convert){
  for (  Map.Entry<String,String> entry : tw2t.entrySet()) {
    String tw=entry.getKey();
    String s=t2s.get(entry.getValue());
    if (s == null)     s=convert ? CharTable.convert(entry.getValue()) : entry.getValue();
    t2s.put(tw,s);
  }
}
