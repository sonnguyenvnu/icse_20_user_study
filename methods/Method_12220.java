@RequestMapping(value="/getNewMessage",produces="text/html") @ResponseBody public String getNewMessage(@RequestParam(value="userid",required=true) String userId,Model model){
  if (StringUtils.isBlank(userId)) {
    return "0";
  }
  List<MiaoShaMessageInfo> miaoShaMessageInfos=messageService.getmessageUserList(Long.valueOf(userId),MessageStatus.ZORE);
  if (miaoShaMessageInfos.isEmpty()) {
    return "0";
  }
 else {
    return "1";
  }
}
