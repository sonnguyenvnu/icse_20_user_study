/** 
 * {@inheritDoc}
 */
@NonNull @Override public Card parseSingleGroup(@Nullable JSONObject data,final ServiceManager serviceManager){
  if (data == null) {
    return Card.NaN;
  }
  final CardResolver cardResolver=serviceManager.getService(CardResolver.class);
  Preconditions.checkState(cardResolver != null,"Must register CardResolver into ServiceManager first");
  final MVHelper cellResolver=serviceManager.getService(MVHelper.class);
  Preconditions.checkState(cellResolver != null,"Must register CellResolver into ServiceManager first");
  final String cardType=data.optString(Card.KEY_TYPE);
  if (!TextUtils.isEmpty(cardType)) {
    final Card card=cardResolver.create(cardType);
    if (card != null) {
      card.serviceManager=serviceManager;
      card.parseWith(data,cellResolver);
      card.type=data.optInt(Card.KEY_TYPE,-1);
      card.stringType=cardType;
      if (card.isValid()) {
        if (card.style.slidable) {
          return new SlideCard(card);
        }
 else {
          return card;
        }
      }
    }
 else {
      final Card cellCard=new WrapCellCard();
      if (cellCard != null) {
        cellCard.serviceManager=serviceManager;
        cellCard.parseWith(data,cellResolver);
        cellCard.setStringType(TangramBuilder.TYPE_CONTAINER_1C_FLOW);
        if (cellCard.isValid()) {
          return cellCard;
        }
      }
    }
  }
 else {
    LogUtils.w(TAG,"Invalid card type when parse JSON data");
  }
  return Card.NaN;
}
