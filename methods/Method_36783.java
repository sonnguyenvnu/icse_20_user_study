public void unMountView(BaseCell cell,View view){
  if (view instanceof IContainer) {
    ViewBase vb=((IContainer)view).getVirtualView();
    vb.reset();
  }
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
  if (mvResolver.isCompatibleType(cell.stringType)) {
    mvResolver.getCellClass(cell.stringType).cast(cell).unbindView(view);
  }
}
