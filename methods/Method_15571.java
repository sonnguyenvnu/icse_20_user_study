/** 
 * ????
 * @author Lemon
 * @param visitorId
 * @throws Exception
 */
@Override public void verifyLogin() throws Exception {
  if (visitorId == null) {
    throw new NotLoggedInException("????????????");
  }
  if (visitorId instanceof Number) {
    if (((Number)visitorId).longValue() <= 0) {
      throw new NotLoggedInException("????????????");
    }
  }
 else   if (visitorId instanceof String) {
    if (StringUtil.isEmpty(visitorId,true)) {
      throw new NotLoggedInException("????????????");
    }
  }
 else {
    throw new UnsupportedDataTypeException("visitorId ??? Long ? String ???");
  }
}
