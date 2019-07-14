/** 
 * Parse original data with type  {@link T} into model data list with type {@link Card}
 * @param data Original data.
 * @return Parsed data list.
 */
public List<Card> parseData(@Nullable T data){
  List<Card> cardList=mDataParser.parseGroup(data,this);
  MVHelper mvHelper=(MVHelper)mServices.get(MVHelper.class);
  if (mvHelper != null) {
    mvHelper.renderManager().onDownloadTemplate();
  }
  return cardList;
}
