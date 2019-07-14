public void mountView(BaseCell cell,View view){
  try {
    mvResolver.register(cell,view);
    if (cell.serviceManager != null) {
      if (cell.serviceManager.supportRx()) {
        cell.emitNext(BDE.BIND);
      }
      CellSupport cellSupport=cell.serviceManager.getService(CellSupport.class);
      if (cellSupport != null) {
        cellSupport.bindView(cell,view);
      }
    }
    boolean renderServiceSuccess=renderManager.mountView(cell,view);
    if (!renderServiceSuccess) {
      initView(cell,view);
    }
    renderStyle(cell,view);
    postMountView(cell,view);
    if (cell.serviceManager != null) {
      CellSupport cellSupport=cell.serviceManager.getService(CellSupport.class);
      if (cellSupport != null) {
        cellSupport.postBindView(cell,view);
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
    if (cell.serviceManager != null) {
      CellSupport cellSupport=cell.serviceManager.getService(CellSupport.class);
      if (cellSupport != null) {
        cellSupport.onBindViewException(cell,view,e);
      }
    }
  }
}
