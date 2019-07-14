/** 
 * ????
 * @param name
 * @param chName
 * @param email
 * @param mobile
 * @param type
 * @param userId
 * @return
 */
@RequestMapping(value="/changeAppUserInfo") public ModelAndView doAddUser(HttpServletRequest request,HttpServletResponse response,Model model,String name,String chName,String email,String mobile,Integer type,Long userId){
  AppUser appUser=AppUser.buildFrom(userId,name,chName,email,mobile,type);
  try {
    if (userId == null) {
      userService.save(appUser);
    }
 else {
      userService.update(appUser);
    }
    write(response,String.valueOf(SuccessEnum.SUCCESS.value()));
  }
 catch (  Exception e) {
    write(response,String.valueOf(SuccessEnum.FAIL.value()));
    logger.error(e.getMessage(),e);
  }
  return null;
}
