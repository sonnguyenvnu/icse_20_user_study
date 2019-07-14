private void rebroadcast(String text){
  RebroadcastBroadcastManager.getInstance().write(mBroadcastResource.getEffectiveBroadcast(),text,getActivity());
  updateRebroadcastStatus();
}
