/** 
 * ????????
 * @return
 */
@RequestMapping(params="user") public String user(HttpServletRequest request){
  List<TSDepart> departList=systemService.getList(TSDepart.class);
  request.setAttribute("departsReplace",RoletoJson.listToReplaceStr(departList,"departname","id"));
  departList.clear();
  return "system/user/userList";
}
