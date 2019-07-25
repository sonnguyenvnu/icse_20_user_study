private void init(ConnectionManager connMan,StructureManager strucMan,SceneManager sceneMan,ManagerStatusListener statusListener,EventListener eventListener){
  this.connMan=connMan;
  this.digitalSTROMClient=connMan.getDigitalSTROMAPI();
  this.config=connMan.getConfig();
  if (strucMan != null) {
    this.strucMan=strucMan;
  }
 else {
    this.strucMan=new StructureManagerImpl();
  }
  if (sceneMan != null) {
    this.sceneMan=sceneMan;
  }
 else {
    this.sceneMan=new SceneManagerImpl(connMan,strucMan,statusListener);
  }
  this.statusListener=statusListener;
  this.eventListener=eventListener;
}
