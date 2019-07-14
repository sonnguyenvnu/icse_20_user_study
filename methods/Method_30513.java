public String getRebroadcastedBy(Context context){
  return rebroadcastedBroadcast != null ? context.getString(R.string.broadcast_rebroadcasted_by_format,getAuthorName()) : null;
}
