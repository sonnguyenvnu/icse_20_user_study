public static int cnt(ST_dt_s d,STARSTAR<ST_dtlink_s> set){
  ENTERING("abaldeo2ie6zi60cazxp7rv47","cnt");
  try {
    int rv;
    dtrestore(d,set.getMe());
    rv=dtsize_(d);
    set.setMe(dtextract(d));
    return rv;
  }
  finally {
    LEAVING("abaldeo2ie6zi60cazxp7rv47","cnt");
  }
}
