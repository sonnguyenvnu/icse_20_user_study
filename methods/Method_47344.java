public static boolean isRoot(String dir){
  return !dir.contains(OTGUtil.PREFIX_OTG) && !dir.startsWith("/storage");
}
