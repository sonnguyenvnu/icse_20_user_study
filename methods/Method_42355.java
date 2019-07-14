/** 
 * ????????.
 */
public void launchAutoSett(){
  List<RpAccount> list=rpAccountQueryService.listAll();
  settBiz.launchAutoSett(list);
}
