public static boolean saveEntrySetToTxt(Set<Map.Entry<Object,Object>> entrySet,String path,String separator){
  StringBuilder sbOut=new StringBuilder();
  for (  Map.Entry<Object,Object> entry : entrySet) {
    sbOut.append(entry.getKey());
    sbOut.append(separator);
    sbOut.append(entry.getValue());
    sbOut.append('\n');
  }
  return saveTxt(path,sbOut.toString());
}
