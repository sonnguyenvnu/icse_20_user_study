private void finish(){
  inProgress=false;
  Intent intent=new Intent(ACTION_FINISHED);
  intent.putExtra(STATE,state.name());
  LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
}
