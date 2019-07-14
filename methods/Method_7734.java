public void destroy(){
  if (currentRequestNum != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(currentRequestNum,true);
    currentRequestNum=0;
  }
}
