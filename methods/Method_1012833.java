/** 
 * Get the role list
 * @return the role list String
 */
@RequestMapping(value="/auth",method={RequestMethod.POST,RequestMethod.GET}) public String auth(){
  List<String> roleNames=userService.getCurrentUserAllRoles();
  LOGGER.info(Constants.LOG_PREFIX + "Page permission loading   roles {}",roleNames);
  return ResultBody.success(roleNames);
}
