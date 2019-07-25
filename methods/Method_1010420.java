public static boolean same(String path1,String path2){
  if (path1.equals(path2)) {
    return true;
  }
  if (path1.length() == path2.length()) {
    return false;
  }
  if (path1.length() > path2.length()) {
{
      Tuples._2<String,String> _tmp_rkp2iv_a0c0n=MultiTuple.<String,String>from(path2,path1);
      path1=_tmp_rkp2iv_a0c0n._0();
      path2=_tmp_rkp2iv_a0c0n._1();
    }
  }
  return path2.startsWith(path1) && path2.charAt(path1.length()) == SLASH_CHAR && (path2.length() - path1.length() == 1);
}
