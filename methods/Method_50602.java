/** 
 * Gets the apex version this class has been compiled with. Use  {@link Version} to compare, e.g.{@code node.getApexVersion() >= Version.V176.getExternal()}
 * @return the apex version
 */
public double getApexVersion(){
  return getNode().getDefiningType().getCodeUnitDetails().getVersion().getExternal();
}
