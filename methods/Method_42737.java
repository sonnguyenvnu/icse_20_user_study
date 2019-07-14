/** 
 */
@RequiresPermissions("sett:record:add") @RequestMapping(value="/launchSett",method=RequestMethod.POST) public String launchSett(HttpServletRequest request,Model model,DwzAjax dwz){
  String userNo=request.getParameter("user.userNo");
  String settAmount=request.getParameter("settAmount");
  rpSettHandleService.launchSett(userNo,new BigDecimal(settAmount));
  dwz.setStatusCode(DWZ.SUCCESS);
  dwz.setMessage(DWZ.SUCCESS_MSG);
  model.addAttribute("dwz",dwz);
  return DWZ.AJAX_DONE;
}
