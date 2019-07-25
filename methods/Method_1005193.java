public StringBuffer end(){
  StringBuffer sb=this.getTagCache();
  if (sb != null) {
    return sb;
  }
  sb=new StringBuffer();
  if (style.equals("easyui")) {
    sb.append("<ul id=\"nav\" class=\"easyui-tree tree-lines\" fit=\"true\" border=\"false\">");
    sb.append(ListtoMenu.getEasyuiMultistageTree(menuFun,style));
    sb.append("</ul>");
  }
  if (style.equals("shortcut")) {
    sb.append("<div id=\"nav\" style=\"display:block;\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">");
    sb.append(ListtoMenu.getEasyuiMultistageTree(menuFun,style));
    sb.append("</div>");
  }
  if (style.equals("bootstrap")) {
    sb.append(ListtoMenu.getBootMenu(parentFun,childFun));
  }
  if (style.equals("json")) {
    sb.append("<script type=\"text/javascript\">");
    sb.append("var _menus=" + ListtoMenu.getMenu(parentFun,childFun));
    sb.append("</script>");
  }
  if (style.equals("june_bootstrap")) {
    sb.append(ListtoMenu.getBootstrapMenu(menuFun));
  }
  if (style.equals("ace")) {
    sb.append(ListtoMenu.getAceMultistageTree(menuFun));
  }
  if (style.equals("diy")) {
    sb.append(ListtoMenu.getDIYMultistageTree(menuFun));
  }
  if (style.equals("hplus")) {
    sb.append(ListtoMenu.getHplusMultistageTree(menuFun));
  }
 else   if (style.equals("fineui")) {
    sb.append(ListtoMenu.getFineuiMultistageTree(menuFun));
  }
  if (style.equals("adminlte")) {
    sb.append(ListtoMenu.getAdminlteTree(menuFun));
  }
  this.putTagCache(sb);
  return sb;
}
