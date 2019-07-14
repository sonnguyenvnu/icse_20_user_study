private void copyText(){
  Broadcast broadcast=mBroadcastAdapter.getBroadcast();
  Activity activity=getActivity();
  if (broadcast == null) {
    ToastUtils.show(R.string.broadcast_copy_text_not_loaded,activity);
    return;
  }
  ClipboardUtils.copy(broadcast,activity);
}
