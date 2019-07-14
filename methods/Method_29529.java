/** 
 * ????????
 * @return
 */
@RequestMapping(value="/addHorizontalNodes") public ModelAndView doAddHorizontalNodes(HttpServletRequest request,HttpServletResponse response,Model model,String masterSizeSlave,Long appAuditId){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {} addHorizontalNodes:{}",appUser.getName(),masterSizeSlave);
  boolean isAdd=false;
  AppAudit appAudit=appService.getAppAuditById(appAuditId);
  String[] configArr=masterSizeSlave.split(ConstUtils.COLON);
  String masterHost=configArr[0];
  String memSize=configArr[1];
  int memSizeInt=NumberUtils.toInt(memSize);
  String slaveHost=null;
  if (configArr.length >= 3) {
    slaveHost=configArr[2];
  }
  try {
    isAdd=appDeployCenter.addHorizontalNodes(appAudit.getAppId(),masterHost,slaveHost,memSizeInt);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  logger.warn("addAppClusterSharding:{}, result is {}",masterSizeSlave,isAdd);
  model.addAttribute("status",isAdd ? 1 : 0);
  return new ModelAndView("");
}
