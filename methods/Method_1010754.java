/** 
 * Disposes of instance properly
 */
public void dispose(){
  myEditorContext.getSelectionManager().removeSelectionListener(mySelectionListener);
}
