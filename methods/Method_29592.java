/** 
 * ??????
 */
@RequestMapping(value="/register") public ModelAndView userRegister(HttpServletRequest request,HttpServletResponse response,Model model,Integer success){
  model.addAttribute("success",success);
  return new ModelAndView("user/userRegister");
}
