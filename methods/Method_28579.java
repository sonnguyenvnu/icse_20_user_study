public PtrHandler getPtrHandler(){
  if (ptrHandler == null) {
    ptrHandler=new PtrDefaultHandler(){
      @Override public boolean checkCanDoRefresh(      PtrFrameLayout frame,      View content,      View header){
        return PtrDefaultHandler.checkContentCanBePulledDown(frame,content,header);
      }
      @Override public void onRefreshBegin(      PtrFrameLayout frame){
        ptrFrameLayout.refreshComplete();
      }
    }
;
  }
  return ptrHandler;
}
