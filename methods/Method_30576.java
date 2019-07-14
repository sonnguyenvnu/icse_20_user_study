public String getText(Context context){
  return hasBroadcast() ? text : context.getString(R.string.broadcast_rebroadcasts_simple_rebroadcast_text);
}
