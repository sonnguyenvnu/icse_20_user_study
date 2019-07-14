/** 
 */
@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET}) public String list(RpPayWay rpPayWay,PageParam pageParam,Model model){
  if (!StringUtil.isEmpty(rpPayWay.getPayProductCode()) && rpPayWay.getPayProductCode().contains(",")) {
    String[] payProductCodes=rpPayWay.getPayProductCode().split(",");
    rpPayWay.setPayProductCode(payProductCodes[0]);
  }
  RpPayProduct rpPayProduct=rpPayProductService.getByProductCode(rpPayWay.getPayProductCode(),null);
  PageBean pageBean=rpPayWayService.listPage(pageParam,rpPayWay);
  model.addAttribute("pageBean",pageBean);
  model.addAttribute("pageParam",pageParam);
  model.addAttribute("rpPayWay",rpPayWay);
  model.addAttribute("rpPayProduct",rpPayProduct);
  return "pay/way/list";
}
