/** 
 * Executes the specified action, repeating and recording it as necessary.
 * @param listener The action listener
 * @param source The event source
 * @param actionCommand The action command
 */
public void executeAction(ActionListener listener,Object source,String actionCommand){
  ActionEvent evt=new ActionEvent(source,ActionEvent.ACTION_PERFORMED,actionCommand);
  if (listener instanceof Wrapper) {
    listener.actionPerformed(evt);
    return;
  }
  boolean _repeat=repeat;
  int _repeatCount=getRepeatCount();
  if (listener instanceof InputHandler.NonRepeatable) {
    listener.actionPerformed(evt);
  }
 else {
    for (int i=0; i < Math.max(1,repeatCount); i++)     listener.actionPerformed(evt);
  }
  if (grabAction == null) {
    if (recorder != null) {
      if (!(listener instanceof InputHandler.NonRecordable)) {
        if (_repeatCount != 1) {
          recorder.actionPerformed(REPEAT,String.valueOf(_repeatCount));
        }
        recorder.actionPerformed(listener,actionCommand);
      }
    }
    if (_repeat) {
      repeat=false;
      repeatCount=0;
    }
  }
}
