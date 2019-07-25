public static boolean mergeable(ST_Agedge_s e,ST_Agedge_s f){
  ENTERING("bg5r9wlego0d8pv0hr96zt45c","mergeable");
  try {
    if (e != null && f != null && EQ(agtail(e),agtail(f)) && EQ(aghead(e),aghead(f)) && EQ(ED_label(e),ED_label(f)) && ports_eq(e,f))     return NOT(false);
    return false;
  }
  finally {
    LEAVING("bg5r9wlego0d8pv0hr96zt45c","mergeable");
  }
}
