/** 
 * ?????????
 * @param appId
 * @param model
 */
@RequestMapping(value="/reportData.json",method=RequestMethod.POST) public void reportData(HttpServletRequest request,HttpServletResponse response,Model model){
  String clientVersion=request.getParameter(ClientReportConstant.CLIENT_VERSION);
  if (!checkClientVersion(clientVersion)) {
    return;
  }
  String json=request.getParameter(ClientReportConstant.JSON_PARAM);
  ClientReportBean clientReportBean=checkReportJson(json);
  if (clientReportBean == null) {
    logger.error("reportWrong json: {}",json);
    return;
  }
  String clientIp=IpUtil.getIpAddr(request);
  if (StringUtils.isNotBlank(clientIp)) {
    clientReportBean.setClientIp(clientIp);
  }
  boolean result=clientReportDataService.deal(clientReportBean);
  if (!result) {
    logger.error("ClientReportDataService deal fail, clientReportBean is {}",clientReportBean);
  }
}
