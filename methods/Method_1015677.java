public static int size(Header[] hdrs,short... excluded_ids){
  int retval=0;
  if (hdrs == null)   return retval;
  for (  Header hdr : hdrs) {
    if (hdr == null)     break;
    if (!Util.containsId(hdr.getProtId(),excluded_ids))     retval++;
  }
  return retval;
}
