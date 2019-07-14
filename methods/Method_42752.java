/** 
 * ???????????
 * @return operateSuccess or operateError .
 */
@RequiresPermissions("pms:operator:edit") @RequestMapping("/edit") public String editPmsOperator(HttpServletRequest req,PmsOperator operator,String selectVal,Model model,DwzAjax dwz){
  try {
    Long id=operator.getId();
    PmsOperator pmsOperator=pmsOperatorService.getDataById(id);
    if (pmsOperator == null) {
      return operateError("?????????????",model);
    }
    if ("USER".equals(this.getPmsOperator().getType()) && "ADMIN".equals(pmsOperator.getType())) {
      return operateError("????",model);
    }
    pmsOperator.setRemark(operator.getRemark());
    pmsOperator.setMobileNo(operator.getMobileNo());
    pmsOperator.setRealName(operator.getRealName());
    String roleOperatorStr=getRoleOperatorStr(selectVal);
    String validateMsg=validatePmsOperator(pmsOperator,roleOperatorStr);
    if (StringUtils.isNotBlank(validateMsg)) {
      return operateError(validateMsg,model);
    }
    pmsOperatorService.updateOperator(pmsOperator,roleOperatorStr);
    return operateSuccess(model,dwz);
  }
 catch (  Exception e) {
    log.error("== editPmsOperator exception:",e);
    return operateError("?????????",model);
  }
}
