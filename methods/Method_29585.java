/** 
 * ??????
 */
@RequestMapping(value="/add") public ModelAndView addNotice(HttpServletRequest request,HttpServletResponse response,Model model){
  String notice=request.getParameter("notice");
  boolean result=appEmailUtil.noticeAllUser(notice);
  model.addAttribute("success",result ? SuccessEnum.SUCCESS.value() : SuccessEnum.FAIL.value());
  return new ModelAndView("");
}
