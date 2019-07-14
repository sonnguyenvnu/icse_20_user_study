/** 
 */
@RequestMapping(value="/lookupList",method={RequestMethod.POST,RequestMethod.GET}) public String lookupList(RpUserInfo rpUserInfo,PageParam pageParam,Model model){
  PageBean pageBean=rpUserInfoService.listPage(pageParam,rpUserInfo);
  model.addAttribute("pageBean",pageBean);
  model.addAttribute("pageParam",pageParam);
  model.addAttribute("rpUserInfo",rpUserInfo);
  return "user/info/lookupList";
}
