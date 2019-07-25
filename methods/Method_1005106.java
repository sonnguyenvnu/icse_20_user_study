/** 
 * ????
 * @param depart
 * @param request
 * @return
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(TSDepart depart,HttpServletRequest request){
  AjaxJson j=new AjaxJson();
  depart=systemService.getEntity(TSDepart.class,depart.getId());
  Long childCount=systemService.getCountForJdbcParam("select count(1) from t_s_depart where parentdepartid = ?",depart.getId());
  if (childCount > 0) {
    j.setSuccess(false);
    j.setMsg("???,????");
    return j;
  }
  systemService.executeSql("delete from t_s_role_org where org_id=?",depart.getId());
  j.setMsg("????");
  return j;
}
