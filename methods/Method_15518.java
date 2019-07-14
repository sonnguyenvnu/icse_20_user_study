/** 
 * ??[] -> []/i ????getAbsPath????name????????
 * @param parentPath
 * @param valuePath
 * @return
 */
public static String replaceArrayChildPath(String parentPath,String valuePath){
  String[] ps=StringUtil.split(parentPath,"]/");
  if (ps != null && ps.length > 1) {
    String[] vs=StringUtil.split(valuePath,"]/");
    if (vs != null && vs.length > 0) {
      String pos;
      for (int i=0; i < ps.length - 1; i++) {
        if (ps[i] == null || ps[i].equals(vs[i]) == false) {
          break;
        }
        pos=ps[i + 1].contains("/") == false ? ps[i + 1] : ps[i + 1].substring(0,ps[i + 1].indexOf("/"));
        if (vs[i + 1].startsWith(pos + "/") == false) {
          vs[i + 1]=pos + "/" + vs[i + 1];
        }
      }
      return StringUtil.getString(vs,"]/");
    }
  }
  return valuePath;
}
