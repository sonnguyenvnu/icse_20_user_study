/** 
 * Test if a room member fields contains a dedicated pattern. The check is done with the displayname and the userId.
 * @param aPattern the pattern to search.
 * @return true if it matches.
 */
public boolean contains(String aPattern){
  if (TextUtils.isEmpty(aPattern)) {
    return false;
  }
  boolean res=false;
  if (!TextUtils.isEmpty(mLowerCaseDisplayName)) {
    res=mLowerCaseDisplayName.contains(aPattern);
  }
  if (!res && !TextUtils.isEmpty(mLowerCaseMatrixId)) {
    res=mLowerCaseMatrixId.contains(aPattern);
  }
  if (!res && (null != mContact)) {
    res=mContact.contains(aPattern);
  }
  return res;
}
