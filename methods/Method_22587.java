/** 
 * Setup additional elements that are only required when running with a GUI, rather than from the command-line. Note that this will not be called when the Mode is used from the command line (because Base will be null).
 */
public void setupGUI(){
  try {
    theme=new Settings(Platform.getContentFile("lib/theme.txt"));
    File modeTheme=new File(folder,"theme/theme.txt");
    if (modeTheme.exists()) {
      theme.load(modeTheme);
    }
    File sketchbookTheme=new File(Base.getSketchbookFolder(),"theme.txt");
    if (sketchbookTheme.exists()) {
      theme.load(sketchbookTheme);
    }
    theme.setColor("run.window.bgcolor",SystemColor.control);
  }
 catch (  IOException e) {
    Messages.showError("Problem loading theme.txt","Could not load theme.txt, please re-install Processing",e);
  }
}
