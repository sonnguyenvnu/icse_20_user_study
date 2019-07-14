public static boolean isStorage(String path){
  for (  String s : DataUtils.getInstance().getStorages())   if (s.equals(path))   return true;
  return false;
}
