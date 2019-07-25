public void accept(MutableEvent event){
  mutableQueue.add(event);
  try {
    HashCasPair hashCasPair=dht.getSigningKey(event.writer).thenApply(signer -> HashCasPair.fromCbor(CborObject.fromByteArray(signer.get().unsignMessage(event.writerSignedBtreeRootHash)))).get();
    Set<PublicKeyHash> updatedOwned=WriterData.getDirectOwnedKeys(event.writer,hashCasPair.updated,dht).join();
    Stat current=state.currentView.get(event.writer);
    for (    PublicKeyHash owned : updatedOwned) {
      state.currentView.computeIfAbsent(owned,k -> new Stat(current.owner,MaybeMultihash.empty(),0,Collections.emptySet()));
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
