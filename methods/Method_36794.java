public void setCards(List<Card> list){
  for (  Card card : list) {
    if (!TextUtils.isEmpty(card.id)) {
      idCardMap.put(card.id,card);
    }
  }
}
