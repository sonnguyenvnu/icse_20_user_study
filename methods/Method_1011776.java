public void show(Collection<SNodeId> selection){
  myRoot=selection.iterator().next();
  myHistoryExtractor=new RootHistoryModel(myRevisions,myRoot,new Runnable(){
    public void run(){
      myUpdateQueue.queue(new Update(this){
        @Override public void run(){
          updateStatusLine();
          updateRevisionList();
        }
      }
);
    }
  }
);
  BackgroundTaskUtil.executeOnPooledThread(this,myHistoryExtractor);
  updateRevisionList();
  show();
}
