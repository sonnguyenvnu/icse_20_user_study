@RequestMapping(value="/checkUserNameExist") public ModelAndView doCheckUserNameExist(HttpServletRequest request,HttpServletResponse response,Model model,String userName){
  AppUser appUser=userService.getByName(userName);
  if (appUser != null) {
    write(response,String.valueOf(SuccessEnum.SUCCESS.value()));
  }
 else {
    write(response,String.valueOf(SuccessEnum.FAIL.value()));
  }
  return null;
}
