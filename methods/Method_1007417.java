/** 
 * Creates and returns a new <code>VersionRange</code> that is a restriction of this version range and the specified version range. <p> Note: Precedence is given to the recommended version from this version range over the recommended version from the specified version range. </p>
 * @param restriction the <code>VersionRange</code> that will be used to restrict this version range.
 * @return the <code>VersionRange</code> that is a restriction of thisversion range and the specified version range. <p> The restrictions of the returned version range will be an intersection of the restrictions of this version range and the specified version range if both version ranges have restrictions. Otherwise, the restrictions on the returned range will be empty. </p> <p> The recommended version of the returned version range will be the recommended version of this version range, provided that ranges falls within the intersected restrictions. If the restrictions are empty, this version range's recommended version is used if it is not <code>null</code>. If it is <code>null</code>, the specified version range's recommended version is used (provided it is non-<code>null</code>). If no recommended version can be obtained, the returned version range's recommended version is set to <code>null</code>. </p>
 * @throws NullPointerException if the specified <code>VersionRange</code> is <code>null</code>.
 */
public VersionRange restrict(VersionRange restriction){
  List<Restriction> r1=this.restrictions;
  List<Restriction> r2=restriction.restrictions;
  List<Restriction> restrictions;
  if (r1.isEmpty() || r2.isEmpty()) {
    restrictions=Collections.emptyList();
  }
 else {
    restrictions=intersection(r1,r2);
  }
  ArtifactVersion version=null;
  if (restrictions.size() > 0) {
    for (    Restriction r : restrictions) {
      if (recommendedVersion != null && r.containsVersion(recommendedVersion)) {
        version=recommendedVersion;
        break;
      }
 else       if (version == null && restriction.getRecommendedVersion() != null && r.containsVersion(restriction.getRecommendedVersion())) {
        version=restriction.getRecommendedVersion();
      }
    }
  }
 else   if (recommendedVersion != null) {
    version=recommendedVersion;
  }
 else   if (restriction.recommendedVersion != null) {
    version=restriction.recommendedVersion;
  }
  return new VersionRange(version,restrictions);
}
