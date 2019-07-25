/** 
 * reference of Spring Web
 */
public boolean includes(MimeType other){
  if (other == null) {
    return false;
  }
  if (this.isWildcardType()) {
    return true;
  }
 else   if (getType().equals(other.getType())) {
    if (getSubType().equals(other.getSubType())) {
      return true;
    }
    if (this.isWildcardSubtype()) {
      int thisPlusIdx=getSubType().indexOf('+');
      if (thisPlusIdx == -1) {
        return true;
      }
 else {
        int otherPlusIdx=other.getSubType().indexOf('+');
        if (otherPlusIdx != -1) {
          String thisSubtypeNoSuffix=getSubType().substring(0,thisPlusIdx);
          String thisSubtypeSuffix=getSubType().substring(thisPlusIdx + 1);
          String otherSubtypeSuffix=other.getSubType().substring(otherPlusIdx + 1);
          if (thisSubtypeSuffix.equals(otherSubtypeSuffix) && WILDCARD_TYPE.equals(thisSubtypeNoSuffix)) {
            return true;
          }
        }
      }
    }
  }
  return false;
}
