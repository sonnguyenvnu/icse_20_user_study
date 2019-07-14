/** 
 * ???????
 * @return
 */
@RequestMapping(value="/initNotice") public ModelAndView init(HttpServletRequest request,HttpServletResponse response,Model model){
  String notice="";
  model.addAttribute("notice",notice);
  model.addAttribute("success",request.getParameter("success"));
  model.addAttribute("noticeActive",SuccessEnum.SUCCESS.value());
  return new ModelAndView("manage/notice/initNotice");
}
