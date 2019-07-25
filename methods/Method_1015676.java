public static int size(Header[] hdrs){
  int retval=0;
  if (hdrs == null)   return retval;
  for (  Header hdr : hdrs) {
    if (hdr == null)     break;
    retval++;
  }
  return retval;
}
