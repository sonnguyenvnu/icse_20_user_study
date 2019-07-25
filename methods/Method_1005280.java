/** 
 * ????? <ul> ??????????? <li>????? ????</li> </ul> <ul> ?????????? <li>????? ????</li> </ul> <ul> ????? ?????? ? ?????? <li>?? ????-?? ??</li> <li>?? ???? ??</li> </ul>
 * @return ???????
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(TSDepart depart,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  depart=systemService.getEntity(TSDepart.class,depart.getId());
  message=MutiLangUtil.paramDelSuccess("common.department");
  if (depart.getTSDeparts().size() == 0) {
    Long userCount=systemService.getCountForJdbcParam("select count(1) from t_s_user_org where org_id = ?",depart.getId());
    if (userCount == 0) {
      systemService.executeSql("delete from t_s_role_org where org_id=?",depart.getId());
      systemService.delete(depart);
      systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
    }
 else {
      message=MutiLangUtil.getLang("common.department.hasuser");
    }
  }
 else {
    message=MutiLangUtil.paramDelFail("common.department");
  }
  j.setMsg(message);
  return j;
}
