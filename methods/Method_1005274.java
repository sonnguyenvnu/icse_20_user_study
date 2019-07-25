/** 
 * ??????
 * @param icon
 * @param req
 * @return
 */
@RequestMapping(params="addorupdate") public ModelAndView addorupdate(TSIcon icon,HttpServletRequest req){
  if (StringUtil.isNotEmpty(icon.getId())) {
    icon=systemService.getEntity(TSIcon.class,icon.getId());
    req.setAttribute("icon",icon);
  }
  return new ModelAndView("system/icon/icons");
}
