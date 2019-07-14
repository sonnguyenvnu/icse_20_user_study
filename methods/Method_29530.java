/** 
 * ????????
 * @param masterSizeSlave
 * @param appAuditId
 * @return
 */
@RequestMapping(value="/checkHorizontalNodes") public ModelAndView doCheckHorizontalNodes(HttpServletRequest request,HttpServletResponse response,Model model,String masterSizeSlave,Long appAuditId){
  DataFormatCheckResult dataFormatCheckResult=null;
  try {
    dataFormatCheckResult=appDeployCenter.checkHorizontalNodes(appAuditId,masterSizeSlave);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    dataFormatCheckResult=DataFormatCheckResult.fail(ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
  }
  model.addAttribute("status",dataFormatCheckResult.getStatus());
  model.addAttribute("message",dataFormatCheckResult.getMessage());
  return new ModelAndView("");
}
