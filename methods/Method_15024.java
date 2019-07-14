/** 
 * ??
 * @param toPraise
 */
public void praise(boolean toPraise){
  if (data == null || toPraise == data.getIsPraised()) {
    Log.e(TAG,"praiseWork  toPraise == moment.getIsPraise() >> return;");
    return;
  }
  HttpRequest.praiseMoment(momentId,toPraise,toPraise ? HTTP_PRAISE : HTTP_CANCEL_PRAISE,this);
}
