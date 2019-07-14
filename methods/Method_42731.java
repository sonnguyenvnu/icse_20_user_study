/** 
 */
@RequiresPermissions("pay:way:add") @RequestMapping(value="/add",method=RequestMethod.POST) public String add(Model model,RpPayWay rpPayWay,DwzAjax dwz){
  rpPayWayService.createPayWay(rpPayWay.getPayProductCode(),rpPayWay.getPayWayCode(),rpPayWay.getPayTypeCode(),rpPayWay.getPayRate());
  dwz.setStatusCode(DWZ.SUCCESS);
  dwz.setMessage(DWZ.SUCCESS_MSG);
  model.addAttribute("dwz",dwz);
  return DWZ.AJAX_DONE;
}
