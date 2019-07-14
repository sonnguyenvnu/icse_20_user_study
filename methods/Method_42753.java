/** 
 * ??ID????????.
 * @return operateSuccess or operateError .
 */
@RequiresPermissions("pms:operator:changestatus") @RequestMapping("/changeStatus") public String changeOperatorStatus(HttpServletRequest req,PmsOperator operator,Model model,DwzAjax dwz){
  try {
    Long operatorId=operator.getId();
    PmsOperator pmsOperator=pmsOperatorService.getDataById(operatorId);
    if (pmsOperator == null) {
      return operateError("??????????",model);
    }
    if (this.getPmsOperator().getId() == operatorId) {
      return operateError("???????????",model);
    }
    if ("USER".equals(this.getPmsOperator().getType()) && "ADMIN".equals(pmsOperator.getType())) {
      return operateError("????",model);
    }
    if (pmsOperator.getStatus().equals(PublicStatusEnum.ACTIVE.name())) {
      if ("ADMIN".equals(pmsOperator.getType())) {
        return operateError("?" + pmsOperator.getLoginName() + "????????????",model);
      }
      pmsOperator.setStatus(PublicStatusEnum.UNACTIVE.name());
      pmsOperatorService.updateData(pmsOperator);
    }
 else {
      pmsOperator.setStatus(PublicStatusEnum.ACTIVE.name());
      pmsOperatorService.updateData(pmsOperator);
    }
    return operateSuccess(model,dwz);
  }
 catch (  Exception e) {
    log.error("== changeOperatorStatus exception:",e);
    return operateError("???????:" + e.getMessage(),model);
  }
}
