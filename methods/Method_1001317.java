public static void renewlist(ST_elist L){
  ENTERING("3f1re3nfkhxwjjb90kppwuupr","renewlist");
  try {
    int i;
    for (i=L.size; i >= 0; i--)     L.list.set(i,null);
    L.size=0;
  }
  finally {
    LEAVING("3f1re3nfkhxwjjb90kppwuupr","renewlist");
  }
}
