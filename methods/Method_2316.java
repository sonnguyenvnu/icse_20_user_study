@Override public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
  if (null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
    return true;
  }
  String appName=PropertiesFileUtil.getInstance().get("app.name");
  String uiPath=PropertiesFileUtil.getInstance().get("zheng.ui.path");
  request.setAttribute("appName",appName);
  request.setAttribute("uiPath",uiPath);
  CmsMenuExample cmsMenuExample=new CmsMenuExample();
  cmsMenuExample.setOrderByClause("orders asc");
  List<CmsMenu> menus=cmsMenuService.selectByExample(cmsMenuExample);
  request.setAttribute("menus",menus);
  return true;
}
