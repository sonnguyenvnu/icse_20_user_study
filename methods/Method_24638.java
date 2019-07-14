/** 
 * Update variable inspector window. Displays local variables and this fields.
 * @param t suspended thread to retrieve locals and this
 */
protected void updateVariableInspector(ThreadReference t){
  if (!t.isSuspended()) {
    return;
  }
  try {
    if (t.frameCount() == 0) {
      log("call stack empty");
    }
 else {
      final VariableInspector vi=editor.variableInspector();
      final List<DefaultMutableTreeNode> stackTrace=getStackTrace(t);
      final List<VariableNode> locals=getLocals(t,0);
      final String currentLocation=currentLocation(t);
      final List<VariableNode> thisFields=getThisFields(t,0,true);
      final List<VariableNode> declaredThisFields=getThisFields(t,0,false);
      final String thisName=thisName(t);
      javax.swing.SwingUtilities.invokeLater(new Runnable(){
        @Override public void run(){
          vi.updateCallStack(stackTrace,"Call Stack");
          vi.updateLocals(locals,"Locals at " + currentLocation);
          vi.updateThisFields(thisFields,"Class " + thisName);
          vi.updateDeclaredThisFields(declaredThisFields,"Class " + thisName);
          vi.unlock();
          vi.rebuild();
        }
      }
);
    }
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
  }
}
