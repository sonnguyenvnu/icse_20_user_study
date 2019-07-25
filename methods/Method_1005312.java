/** 
 * ??????
 * @return
 */
@RequestMapping(params="role") @ResponseBody public List<ComboBox> role(HttpServletResponse response,HttpServletRequest request,ComboBox comboBox){
  String id=request.getParameter("id");
  List<ComboBox> comboBoxs=new ArrayList<ComboBox>();
  List<TSRole> roles=new ArrayList<TSRole>();
  if (StringUtil.isNotEmpty(id)) {
    List<TSRoleUser> roleUser=systemService.findByProperty(TSRoleUser.class,"TSUser.id",id);
    if (roleUser.size() > 0) {
      for (      TSRoleUser ru : roleUser) {
        roles.add(ru.getTSRole());
      }
    }
  }
  List<TSRole> roleList=systemService.getList(TSRole.class);
  comboBoxs=TagUtil.getComboBox(roleList,roles,comboBox);
  roleList.clear();
  roles.clear();
  return comboBoxs;
}
