@Override public boolean call(){
  mReceiver=new GenericStringResultReceiver();
  passToBrowser(GET_STATE_COMMAND,mStatesId);
  return true;
}
