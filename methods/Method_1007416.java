/** 
 * Any.
 * @return the version range
 */
public static VersionRange any(){
  return new VersionRange(new ArtifactVersion(""),Collections.singletonList(Restriction.NONE));
}
