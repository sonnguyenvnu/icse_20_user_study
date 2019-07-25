/** 
 * ???????????
 * @param request
 * @return
 * @throws Exception
 */
@SuppressWarnings("deprecation") @RequestMapping(params="update",method=RequestMethod.POST) @ResponseBody public AjaxJson update(HttpServletRequest request) throws Exception {
  String message=null;
  AjaxJson j=new AjaxJson();
  Short iconType=oConvertUtils.getShort(request.getParameter("iconType"));
  String iconName=java.net.URLDecoder.decode(oConvertUtils.getString(request.getParameter("iconName")));
  String id=request.getParameter("id");
  TSIcon icon=new TSIcon();
  if (StringUtil.isNotEmpty(id)) {
    icon=systemService.get(TSIcon.class,id);
    icon.setId(id);
  }
  icon.setIconName(iconName);
  icon.setIconType(iconType);
  systemService.saveOrUpdate(icon);
  String css="." + icon.getIconClas() + "{background:url('../images/" + icon.getIconClas() + "." + icon.getExtend() + "') no-repeat}";
  write(request,css);
  message="????";
  j.setMsg(message);
  return j;
}
