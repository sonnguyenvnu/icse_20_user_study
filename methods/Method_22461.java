/** 
 * Returns true if the contribution fits the given property, false otherwise. If the property is invalid, returns false.
 */
private boolean hasProperty(Contribution contrib,String property){
  if (property.startsWith("updat") || property.startsWith("upgrad")) {
    return hasUpdates(contrib);
  }
  if (property.startsWith("instal") && !property.startsWith("installabl")) {
    return contrib.isInstalled();
  }
  if (property.equals("tool")) {
    return contrib.getType() == ContributionType.TOOL;
  }
  if (property.startsWith("lib")) {
    return contrib.getType() == ContributionType.LIBRARY;
  }
  if (property.equals("mode")) {
    return contrib.getType() == ContributionType.MODE;
  }
  return false;
}
