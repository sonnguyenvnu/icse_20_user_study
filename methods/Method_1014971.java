/** 
 * @author wyq????
 */
public void update(@Para("") OaEvent oaEvent){
  boolean oaAuth=AuthUtil.isOaAuth(OaEnum.EVENT_TYPE_KEY.getTypes(),oaEvent.getEventId());
  if (oaAuth) {
    renderJson(R.noAuth());
    return;
  }
  renderJson(oaEventService.update(oaEvent));
}
