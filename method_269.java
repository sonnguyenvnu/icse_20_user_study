/** 
 * Get the extra list of server lifecycle components to enable on a bookie server.
 * @return the extra list of server lifecycle components to enable on a bookie server.
 */
public String[] _XXXXX_(){
  String extraServerComponentsStr=getString(EXTRA_SERVER_COMPONENTS);
  if (Strings.isNullOrEmpty(extraServerComponentsStr)) {
    return null;
  }
  return this.getStringArray(EXTRA_SERVER_COMPONENTS);
}