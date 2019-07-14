/** 
 * ????????
 * @param pId
 * @param buffer
 */
@SuppressWarnings("rawtypes") private void recursionTreeMenu(String pId,StringBuffer buffer,List list,String url){
  if (pId.equals("0")) {
    buffer.append("<ul class=\"tree treeFolder collapse \" >");
  }
 else {
    buffer.append("<ul>");
  }
  List<Map> listMap=getSonMenuListByPid(pId,list);
  for (  Map map : listMap) {
    String id=map.get("id").toString();
    String name=map.get("name").toString();
    String isLeaf=map.get("isLeaf").toString();
    buffer.append("<li><a onclick=\"onClickMenuNode(" + id + ")\"  href=\"" + url + "?id=" + id + "\" target=\"ajax\" rel=\"jbsxBox\"  value=" + id + ">" + name + "</a>");
    if (!isLeaf.equals("1")) {
      recursionTreeMenu(id,buffer,list,url);
    }
    buffer.append("</li>");
  }
  buffer.append("</ul>");
}
