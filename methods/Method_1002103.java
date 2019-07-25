/** 
 * ????????
 * @author fengshuonan
 * @Date 2018/12/24 22:44
 */
@RequestMapping("/reset") @BussinessLog(value="???????",key="userId",dict=UserDict.class) @Permission(Const.ADMIN_NAME) @ResponseBody public ResponseData reset(@RequestParam Long userId){
  if (ToolUtil.isEmpty(userId)) {
    throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
  }
  this.userService.assertAuth(userId);
  User user=this.userService.getById(userId);
  user.setSalt(ShiroKit.getRandomSalt(5));
  user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD,user.getSalt()));
  this.userService.updateById(user);
  return SUCCESS_TIP;
}
