/** 
 * @return The minor version as a string.
 */
public String getMinorAsString(){
  if (versionParts.size() == 1) {
    return "0";
  }
  return versionParts.get(1).toString();
}
