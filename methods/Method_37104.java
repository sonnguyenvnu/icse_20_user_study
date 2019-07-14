public Card findCardById(String id){
synchronized (idCardMap) {
    return idCardMap.get(id);
  }
}
