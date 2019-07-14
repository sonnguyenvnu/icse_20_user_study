/** 
 * Check the position of a specified part.
 * @param part the part to be checked
 * @return part position, THE_FIRST_PART when it's thefirst one, THE_LAST_PART when it's the last one.
 */
private static int checkPartPosition(PduPart part){
  assert (null != part);
  if ((null == mTypeParam) && (null == mStartParam)) {
    return THE_LAST_PART;
  }
  if (null != mStartParam) {
    byte[] contentId=part.getContentId();
    if (null != contentId) {
      if (true == Arrays.equals(mStartParam,contentId)) {
        return THE_FIRST_PART;
      }
    }
  }
  if (null != mTypeParam) {
    byte[] contentType=part.getContentType();
    if (null != contentType) {
      if (true == Arrays.equals(mTypeParam,contentType)) {
        return THE_FIRST_PART;
      }
    }
  }
  return THE_LAST_PART;
}
