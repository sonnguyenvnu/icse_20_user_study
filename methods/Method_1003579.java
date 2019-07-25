/** 
 * Indicate whether this  {@code MediaType} includes the given media type.<p>For instance,  {@code text/*} includes {@code text/plain} and {@code text/html}, and  {@code application/*+xml}includes  {@code application/soap+xml}, etc. This method is <b>not</b> symmetric.
 * @param other the reference media type with which to compare.
 * @return true if this media type includes the given media type, false otherwise.
 */
public boolean includes(MimeType other){
  if (other == null) {
    return false;
  }
  if (this.isWildcardType()) {
    return true;
  }
 else   if (getType().equals(other.getType())) {
    if (getSubtype().equals(other.getSubtype())) {
      return true;
    }
    if (this.isWildcardSubtype()) {
      int thisPlusIdx=getSubtype().indexOf('+');
      if (thisPlusIdx == -1) {
        return true;
      }
 else {
        int otherPlusIdx=other.getSubtype().indexOf('+');
        if (otherPlusIdx != -1) {
          String thisSubtypeNoSuffix=getSubtype().substring(0,thisPlusIdx);
          String thisSubtypeSuffix=getSubtype().substring(thisPlusIdx + 1);
          String otherSubtypeSuffix=other.getSubtype().substring(otherPlusIdx + 1);
          if (thisSubtypeSuffix.equals(otherSubtypeSuffix) && WILDCARD_TYPE.equals(thisSubtypeNoSuffix)) {
            return true;
          }
        }
      }
    }
  }
  return false;
}
