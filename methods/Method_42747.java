/** 
 * ???????????????????????.
 * @return listPmsOperator or operateError .
 */
@RequiresPermissions("pms:operator:view") @RequestMapping("/list") public String listPmsOperator(HttpServletRequest req,PageParam pageParam,PmsOperator operator,Model model){
  try {
    PageBean pageBean=pmsOperatorService.listPage(pageParam,operator);
    model.addAttribute(pageBean);
    model.addAttribute("OperatorStatusEnum",PublicStatusEnum.toMap());
    model.addAttribute("OperatorTypeEnum",OperatorTypeEnum.toMap());
    return "pms/pmsOperatorList";
  }
 catch (  Exception e) {
    log.error("== listPmsOperator exception:",e);
    return operateError("??????",model);
  }
}
