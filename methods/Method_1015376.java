/** 
 * ?????????
 * @param mailNickname
 * @param appId
 * @param isCollect
 * @return
 */
@GetMapping("/isNeedCollect") public ResultData collected(@RequestParam(value="mailNickname") String mailNickname,@RequestParam(value="appId") String appId,@RequestParam(value="isCollect") boolean isCollect){
  if (isCollect) {
    userAppService.collecteApp(mailNickname,appId.toLowerCase());
  }
 else {
    userAppService.cancleCollecteApp(mailNickname,appId.toLowerCase());
  }
  return ResultData.builder().build();
}
