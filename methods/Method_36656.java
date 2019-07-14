@Override public boolean mountView(JSONObject json,View view){
  ViewBase vb=((IContainer)view).getVirtualView();
  vb.setVData(json);
  if (vb.supportExposure()) {
    vafContext.getEventManager().emitEvent(EventManager.TYPE_Exposure,EventData.obtainData(vafContext,vb));
  }
  return true;
}
