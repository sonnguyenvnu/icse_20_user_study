/** 
 * ????
 * @author fengshuonan
 * @Date 2018/12/23 5:53 PM
 */
@Permission(Const.ADMIN_NAME) @RequestMapping(value="/edit") @BussinessLog(value="????",key="name",dict=MenuDict.class) @ResponseBody public ResponseData edit(MenuDto menu){
  this.menuService.updateMenu(menu);
  this.userService.refreshCurrentUser();
  return SUCCESS_TIP;
}
