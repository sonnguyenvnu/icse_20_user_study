@Override public void unrebroadcastBroadcast(Broadcast broadcast){
  DeleteBroadcastManager.getInstance().write(broadcast,getActivity());
}
