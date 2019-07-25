public void init(){
  myEventsListener=new SModelEventsDispatcher(myProjectTree.getProject().getRepository());
  myProjectTree.addTreeNodeListener(this);
}
