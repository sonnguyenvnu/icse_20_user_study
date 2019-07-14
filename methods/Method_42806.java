/** 
 */
@RequestMapping(value="/getAccountInfo",method={RequestMethod.POST,RequestMethod.GET}) public String getAccountInfo(HttpServletRequest request){
  RpUserInfo rpUserInfo=(RpUserInfo)request.getSession().getAttribute(ConstantClass.USER);
  String userNo=rpUserInfo.getUserNo();
  RpAccount rpAccount=rpAccountService.getDataByUserNo(userNo);
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByUserNo(userNo);
  List<RpPayWay> rpPayWayList=new ArrayList<RpPayWay>();
  if (rpUserPayConfig != null) {
    rpPayWayList=rpPayWayService.listByProductCode(rpUserPayConfig.getProductCode());
  }
  request.setAttribute("rpAccount",rpAccount);
  request.setAttribute("rpUserPayConfig",rpUserPayConfig);
  request.setAttribute("rpPayWayList",rpPayWayList);
  return "account/info";
}
