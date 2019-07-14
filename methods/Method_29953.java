@Override protected void onSendBroadcast(){
  Activity activity=getActivity();
  activity.startActivity(SendBroadcastActivity.makeTopicIntent(mTopic,activity));
}
