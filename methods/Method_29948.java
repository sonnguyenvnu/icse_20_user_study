public static Intent makeTopicIntent(String topic,Context context){
  return new Intent(context,BroadcastListActivity.class).putExtra(EXTRA_TOPIC,topic);
}
