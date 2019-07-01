public void _XXXXX_(){
  for (int i=0; i < currentEnsemble.size(); i++) {
    bookieClient.readLac(currentEnsemble.get(i),lh.ledgerId,this,i);
  }
}