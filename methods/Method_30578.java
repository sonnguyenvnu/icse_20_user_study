public long getBroadcastId(){
  return Long.parseLong(Uri.parse(uri).getLastPathSegment());
}
