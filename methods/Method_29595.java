/** 
 * ????
 * @param chName ???
 * @return
 */
@RequestMapping(value="/list") public ModelAndView doUserList(HttpServletRequest request,HttpServletResponse response,Model model,String searchChName){
  List<AppUser> users=userService.getUserList(searchChName);
  model.addAttribute("users",users);
  model.addAttribute("searchChName",searchChName);
  model.addAttribute("userActive",SuccessEnum.SUCCESS.value());
  return new ModelAndView("manage/user/list");
}
