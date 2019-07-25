/** 
 * Specify if the  {@link Version} matches this range. Returns {@code true} if theversion is contained within this range,  {@code false} otherwise.
 * @param version the version to check
 * @return {@code true} if the version matches
 */
public boolean match(Version version){
  Assert.notNull(version,"Version must not be null");
  int lower=this.lowerVersion.compareTo(version);
  if (lower > 0) {
    return false;
  }
 else   if (!this.lowerInclusive && lower == 0) {
    return false;
  }
  if (this.higherVersion != null) {
    int higher=this.higherVersion.compareTo(version);
    if (higher < 0) {
      return false;
    }
 else     if (!this.higherInclusive && higher == 0) {
      return false;
    }
  }
  return true;
}
