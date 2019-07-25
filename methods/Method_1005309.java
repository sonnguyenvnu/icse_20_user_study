@RequestMapping(params="changeportrait") public String changeportrait(HttpServletRequest request){
  TSUser user=ResourceUtil.getSessionUser();
  request.setAttribute("user",user);
  return "system/user/changeportrait";
}
