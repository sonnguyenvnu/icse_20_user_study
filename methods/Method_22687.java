/** 
 * Check for a 3.0 sketchbook location, and if none exists, try to grab it from the 2.0 sketchbook location.
 * @return true if a location was found and the pref didn't exist
 */
static protected boolean checkSketchbookPref(){
  if (getSketchbookPath() == null) {
    String twoPath=get("sketchbook.path");
    if (twoPath != null) {
      setSketchbookPath(twoPath);
      return true;
    }
  }
  return false;
}
