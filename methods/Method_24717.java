/** 
 * Checks if the sketch contains java tabs. If it does, the editor ain't built for it, yet. Also, user should really start looking at a full IDE like Eclipse. Disable compilation check and some more features.
 */
private boolean checkForJavaTabs(){
  for (  SketchCode code : getSketch().getCode()) {
    if (code.getExtension().equals("java")) {
      if (!javaTabWarned) {
        System.out.println(getSketch().getName() + " contains .java tabs. ");
        System.out.println("Some editor features (like completion " + "and error checking) will be disabled.");
        javaTabWarned=true;
      }
      return true;
    }
  }
  return false;
}
