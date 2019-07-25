/** 
 * ????
 * @author fengshuonan
 * @Date 2018/12/23 5:53 PM
 */
@Permission(Const.ADMIN_NAME) @RequestMapping(value="/remove") @BussinessLog(value="????",key="menuId",dict=DeleteDict.class) @ResponseBody public ResponseData remove(@RequestParam Long menuId){
  if (ToolUtil.isEmpty(menuId)) {
    throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
  }
  LogObjectHolder.me().set(ConstantFactory.me().getMenuName(menuId));
  this.menuService.delMenuContainSubMenus(menuId);
  return SUCCESS_TIP;
}
