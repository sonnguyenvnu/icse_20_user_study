private void openBroadcast(Broadcast broadcast,View sharedView,boolean showSendComment){
  Activity activity=getActivity();
  Intent intent=BroadcastActivity.makeIntent(broadcast,showSendComment,activity.getTitle().toString(),activity);
  Bundle options=TransitionUtils.makeActivityOptionsBundle(activity,sharedView);
  ActivityCompat.startActivity(activity,intent,options);
}
