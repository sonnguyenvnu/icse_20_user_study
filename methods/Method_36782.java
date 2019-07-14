public void mountView(BaseCell cell,View view){
  try {
    mvResolver.register(getCellUniqueId(cell),cell,view);
    if (cell.serviceManager != null) {
      if (cell.serviceManager.supportRx()) {
        cell.emitNext(BDE.BIND);
      }
      CellSupport cellSupport=cell.serviceManager.getService(CellSupport.class);
      if (cellSupport != null) {
        cellSupport.bindView(cell,view);
      }
    }
    if (view instanceof IContainer) {
      ViewBase vb=((IContainer)view).getVirtualView();
      vb.setVData(cell.extras);
      if (vb.supportExposure()) {
        VafContext context=cell.serviceManager.getService(VafContext.class);
        context.getEventManager().emitEvent(EventManager.TYPE_Exposure,EventData.obtainData(context,vb));
      }
      renderStyle(cell,view);
    }
 else {
      loadMethod(cell,view);
      initView(cell,view);
      renderView(cell,view);
      renderStyle(cell,view);
    }
    if (mvResolver.isCompatibleType(cell.stringType)) {
      mvResolver.getCellClass(cell.stringType).cast(cell).bindView(view);
    }
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
