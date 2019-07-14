/** 
 * Show the Preferences window.
 */
public void handlePrefs(){
  if (preferencesFrame == null) {
    preferencesFrame=new PreferencesFrame(this);
  }
  preferencesFrame.showFrame();
}
