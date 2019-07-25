/** 
 * ????
 * @author fengshuonan
 * @Date 2018/12/23 6:31 PM
 */
@RequestMapping(value="/remove") @BussinessLog(value="????",key="roleId",dict=DeleteDict.class) @Permission(Const.ADMIN_NAME) @ResponseBody public ResponseData remove(@RequestParam Long roleId){
  LogObjectHolder.me().set(ConstantFactory.me().getDeptName(roleId));
  this.roleService.delRoleById(roleId);
  return SUCCESS_TIP;
}
