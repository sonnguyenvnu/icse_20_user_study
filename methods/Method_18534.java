@Override public boolean getFocusable(){
  return getOrCreateNodeInfo().getFocusState() == NodeInfo.FOCUS_SET_TRUE;
}
