/** 
 * ??
 * @param dn
 */
public static void delete(String dn,DirContext dc){
  try {
    dc.destroySubcontext(dn);
  }
 catch (  Exception e) {
    LogUtil.error("Exception in delete():" + e);
  }
}
