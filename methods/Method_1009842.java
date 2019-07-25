/** 
 * Tell if one field contains the pattern
 * @param pattern the pattern to find
 * @return true if it is found.
 */
public boolean contains(String pattern){
  if (TextUtils.isEmpty(pattern)) {
    return false;
  }
  boolean matched=false;
  if (!TextUtils.isEmpty(mDisplayName)) {
    matched=(mDisplayName.toLowerCase(VectorLocale.INSTANCE.getApplicationLocale()).contains(pattern));
  }
  if (!matched) {
    for (    String email : mEmails) {
      matched|=email.toLowerCase(VectorLocale.INSTANCE.getApplicationLocale()).contains(pattern);
    }
  }
  if (!matched) {
    for (    PhoneNumber pn : mPhoneNumbers) {
      matched|=pn.mMsisdnPhoneNumber.toLowerCase(VectorLocale.INSTANCE.getApplicationLocale()).contains(pattern) || pn.mRawPhoneNumber.toLowerCase(VectorLocale.INSTANCE.getApplicationLocale()).contains(pattern) || (pn.mE164PhoneNumber != null && pn.mE164PhoneNumber.toLowerCase(VectorLocale.INSTANCE.getApplicationLocale()).contains(pattern));
    }
  }
  return matched;
}
