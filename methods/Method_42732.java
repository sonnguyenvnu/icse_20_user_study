/** 
 */
@RequiresPermissions("pay:way:edit") @RequestMapping(value="/editUI",method=RequestMethod.GET) public String editUI(Model model,@RequestParam("id") String id){
  RpPayWay rpPayWay=rpPayWayService.getDataById(id);
  model.addAttribute("PayWayEnums",PayWayEnum.toList());
  model.addAttribute("PayTypeEnums",PayTypeEnum.toList());
  model.addAttribute("rpPayWay",rpPayWay);
  return "pay/way/edit";
}
