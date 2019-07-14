/** 
 */
@RequestMapping(value="/historyList",method={RequestMethod.POST,RequestMethod.GET}) public String historyList(RpAccountHistory rpAccountHistory,PageParam pageParam,Model model){
  PageBean pageBean=rpAccountHistoryService.listPage(pageParam,rpAccountHistory);
  List<Object> recordList=pageBean.getRecordList();
  for (  Object obj : recordList) {
    RpAccountHistory history=(RpAccountHistory)obj;
    RpUserInfo userInfo=rpUserInfoService.getDataByMerchentNo(history.getUserNo());
    history.setUserName(userInfo.getUserName());
  }
  model.addAttribute("pageBean",pageBean);
  model.addAttribute("pageParam",pageParam);
  model.addAttribute("rpAccountHistory",rpAccountHistory);
  return "account/historyList";
}
