/** 
 * ???????????.
 */
public void launchDailySettCollect(){
  List<RpAccount> list=rpAccountQueryService.listAll();
  Date endDate=new Date();
  settBiz.launchDailySettCollect(list,endDate);
}
