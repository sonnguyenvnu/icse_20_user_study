/** 
 * Instructs the screen to start a selection. <p> If a selection menu was provided, this menu will be shown instead of the regular one.
 */
public void startSelection(){
  activity.startSupportActionMode(new ActionModeWrapper());
}
