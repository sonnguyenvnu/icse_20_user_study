/** 
 * Function to determine whether or not the example present in the exampleLocation directory is compatible with the current mode.
 * @return true if compatible with the Mode of the currently active editor
 */
static public boolean isCompatible(Mode mode,StringDict props){
  String currentIdentifier=mode.getIdentifier();
  StringList compatibleList=parseModeList(props);
  if (compatibleList.size() == 0) {
    if (mode.requireExampleCompatibility()) {
      return false;
    }
    return true;
  }
  return compatibleList.hasValue(currentIdentifier);
}
