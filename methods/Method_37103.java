public void setCards(List<Card> list){
synchronized (idCardMap) {
    for (    Card card : list) {
      if (!TextUtils.isEmpty(card.id)) {
        idCardMap.put(card.id,card);
      }
    }
  }
}
