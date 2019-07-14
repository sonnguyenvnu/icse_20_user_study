public static String[] splitPathToChunks(final String actionPath){
  String path=StringUtil.cutSurrounding(actionPath,StringPool.SLASH);
  return StringUtil.splitc(path,'/');
}
