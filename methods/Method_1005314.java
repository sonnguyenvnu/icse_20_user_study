/** 
 * easyuiAJAX???????? 
 * @param request
 * @param response
 * @param dataGrid
 */
@RequestMapping(params="datagrid") public void datagrid(TSUser user,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(TSUser.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,user);
  Short[] userstate=new Short[]{Globals.User_Normal,Globals.User_ADMIN,Globals.User_Forbidden};
  cq.in("status",userstate);
  cq.eq("deleteFlag",Globals.Delete_Normal);
  cq.eq("userType",Globals.USER_TYPE_SYSTEM);
  String orgIds=request.getParameter("orgIds");
  List<String> orgIdList=extractIdListByComma(orgIds);
  if (!CollectionUtils.isEmpty(orgIdList)) {
    CriteriaQuery subCq=new CriteriaQuery(TSUserOrg.class);
    subCq.setProjection(Property.forName("tsUser.id"));
    subCq.in("tsDepart.id",orgIdList.toArray());
    subCq.add();
    cq.add(Property.forName("id").in(subCq.getDetachedCriteria()));
  }
  cq.add();
  this.systemService.getDataGridReturn(cq,true);
  List<TSUser> cfeList=new ArrayList<TSUser>();
  for (  Object o : dataGrid.getResults()) {
    if (o instanceof TSUser) {
      TSUser cfe=(TSUser)o;
      if (cfe.getId() != null && !"".equals(cfe.getId())) {
        List<TSRoleUser> roleUser=systemService.findByProperty(TSRoleUser.class,"TSUser.id",cfe.getId());
        if (roleUser.size() > 0) {
          String roleName="";
          for (          TSRoleUser ru : roleUser) {
            roleName+=ru.getTSRole().getRoleName() + ",";
          }
          roleName=roleName.substring(0,roleName.length() - 1);
          cfe.setUserKey(roleName);
        }
      }
      cfeList.add(cfe);
    }
  }
  TagUtil.datagrid(response,dataGrid);
}
