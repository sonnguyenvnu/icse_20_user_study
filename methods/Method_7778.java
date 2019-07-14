public TLRPC.TL_messageMediaVenue getItem(int i){
  if (i >= 0 && i < places.size()) {
    return places.get(i);
  }
  return null;
}
