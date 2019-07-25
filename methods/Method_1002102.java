/** 
 * ???????
 * @author fengshuonan
 * @Date 2018/12/24 22:44
 */
@RequestMapping("/view/{userId}") @ResponseBody public User view(@PathVariable Long userId){
  if (ToolUtil.isEmpty(userId)) {
    throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
  }
  this.userService.assertAuth(userId);
  return this.userService.getById(userId);
}
