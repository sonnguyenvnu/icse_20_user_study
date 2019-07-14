public static String joiner(List<KVValue> lists,String oper){
  if (lists.size() == 0) {
    return null;
  }
  StringBuilder sb=new StringBuilder(lists.get(0).toString());
  for (int i=1; i < lists.size(); i++) {
    sb.append(oper);
    sb.append(lists.get(i).toString());
  }
  return sb.toString();
}
