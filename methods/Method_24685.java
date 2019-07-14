public void handleStep(int modifiers){
  if (modifiers == 0) {
    Messages.log("Invoked 'Step Over' menu item");
    debugger.stepOver();
  }
 else   if ((modifiers & ActionEvent.SHIFT_MASK) != 0) {
    Messages.log("Invoked 'Step Into' menu item");
    debugger.stepInto();
  }
 else   if ((modifiers & ActionEvent.ALT_MASK) != 0) {
    Messages.log("Invoked 'Step Out' menu item");
    debugger.stepOut();
  }
}
