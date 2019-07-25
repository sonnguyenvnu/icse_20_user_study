/** 
 * ????????
 * @author fengshuonan
 * @Date 2018/12/23 5:51 PM
 */
@RequestMapping("/list") @Permission(Const.ADMIN_NAME) @ResponseBody public Object list(@RequestParam(required=false) String beginTime,@RequestParam(required=false) String endTime,@RequestParam(required=false) String logName){
  Page page=LayuiPageFactory.defaultPage();
  List<Map<String,Object>> result=loginLogService.getLoginLogs(page,beginTime,endTime,logName);
  page.setRecords(new LogWrapper(result).wrap());
  return LayuiPageFactory.createPageInfo(page);
}
