/** 
 * ????
 * @param interface
 * @return
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(TSInterfaceEntity tsInterface,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  tsInterface=systemService.getEntity(TSInterfaceEntity.class,tsInterface.getId());
  List<TSInterfaceEntity> ts=tsInterface.getTSInterfaces();
  if (ts != null && ts.size() > 0) {
    message=MutiLangUtil.getLang("common.menu.del.fail");
  }
 else {
    String hql=" from TSInterfaceDdataRuleEntity where TSInterface.id = ?";
    List<Object> findByQueryString=systemService.findHql(hql,tsInterface.getId());
    if (findByQueryString != null && findByQueryString.size() > 0) {
      message=MutiLangUtil.getLang("common.menu.del.fail");
    }
 else {
      String sql="delete from t_s_interface where id = ?";
      systemService.executeSql(sql,tsInterface.getId());
      String interroleSql="delete from t_s_interrole_interface where interface_id = ?";
      systemService.executeSql(interroleSql,tsInterface.getId());
      message=MutiLangUtil.paramDelSuccess("common.menu");
      systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
    }
  }
  j.setMsg(message);
  return j;
}
