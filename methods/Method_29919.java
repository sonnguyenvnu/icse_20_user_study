@Override public void onRebroadcast(Broadcast broadcast,boolean rebroadcast,boolean quick){
  if (rebroadcast) {
    if (quick) {
      RebroadcastBroadcastManager.getInstance().write(broadcast.getEffectiveBroadcast(),null,getActivity());
    }
 else {
      startActivity(RebroadcastBroadcastActivity.makeIntent(broadcast,getActivity()));
    }
  }
 else {
    if (quick) {
      DeleteBroadcastManager.getInstance().write(broadcast,getActivity());
    }
 else {
      ConfirmUnrebroadcastBroadcastDialogFragment.show(broadcast,this);
    }
  }
}
