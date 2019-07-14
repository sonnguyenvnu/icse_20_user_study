public void unMountView(BaseCell cell,View view){
  renderManager.unmountView(cell,view);
  if (cell.serviceManager != null) {
    if (cell.serviceManager.supportRx()) {
      cell.emitNext(BDE.UNBIND);
    }
  }
  postUnMountView(cell,view);
  if (cell.serviceManager != null) {
    CellSupport cellSupport=cell.serviceManager.getService(CellSupport.class);
    if (cellSupport != null) {
      cellSupport.unBindView(cell,view);
    }
  }
}
