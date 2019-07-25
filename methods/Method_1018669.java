@Override public int length(){
  int len=3;
  if (venueInfo != null) {
    len+=2;
  }
  if (hessid != null) {
    len+=6;
  }
  return len;
}
