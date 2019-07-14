/** 
 * Method to close the ClassLoader so that the archives are no longer "locked" and a mode can be removed without restart.
 */
public void clearClassLoader(Base base){
  List<ModeContribution> contribModes=base.getModeContribs();
  int botherToRemove=contribModes.indexOf(this);
  if (botherToRemove != -1) {
    contribModes.remove(botherToRemove);
    try {
      ((URLClassLoader)loader).close();
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
}
