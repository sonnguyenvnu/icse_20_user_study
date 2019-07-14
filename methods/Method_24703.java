/** 
 * Grab current contents of the sketch window, advance the console, stop any other running sketches, auto-save the user's code... not in that order.
 */
@Override public void prepareRun(){
  autoSave();
  super.prepareRun();
  downloadImports();
  preprocessingService.cancel();
}
