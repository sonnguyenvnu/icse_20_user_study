/** 
 * easyuiAJAX????? ????????
 * @param request
 * @param response
 * @param dataGrid
 * @param user
 */
@RequestMapping(params="addorupdate") public ModelAndView addorupdate(TSUser user,HttpServletRequest req){
  List<String> orgIdList=new ArrayList<String>();
  TSDepart tsDepart=new TSDepart();
  if (StringUtil.isNotEmpty(user.getId())) {
    user=systemService.getEntity(TSUser.class,user.getId());
    req.setAttribute("user",user);
    idandname(req,user);
    getOrgInfos(req,user);
  }
 else {
    String departid=oConvertUtils.getString(req.getParameter("departid"));
    if (StringUtils.isNotEmpty(departid)) {
      TSDepart depart=systemService.getEntity(TSDepart.class,departid);
      if (depart != null) {
        req.setAttribute("orgIds",depart.getId() + ",");
        req.setAttribute("departname",depart.getDepartname() + ",");
      }
    }
    String roleId=oConvertUtils.getString(req.getParameter("roleId"));
    if (StringUtils.isNotEmpty(roleId)) {
      TSRole tsRole=systemService.getEntity(TSRole.class,roleId);
      if (tsRole != null) {
        req.setAttribute("id",roleId);
        req.setAttribute("roleName",tsRole.getRoleName());
      }
    }
  }
  req.setAttribute("tsDepart",tsDepart);
  return new ModelAndView("system/user/user");
}
