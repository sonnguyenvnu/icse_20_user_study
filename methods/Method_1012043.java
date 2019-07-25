@Override public void clear(){
  if (RuntimeFlags.isTestMode()) {
    return;
  }
  myUpdateQueue.queue(new Update(new Object()){
    @Override public void run(){
      if (myIsDisposed) {
        return;
      }
      myModel.clear();
      myMessages.clear();
      myErrors=0;
      myWarnings=0;
      myInfos=0;
      myHintObjects=0;
      myList.setFixedCellWidth(myList.getWidth());
      updateHeader();
      updateActions();
    }
  }
);
}
