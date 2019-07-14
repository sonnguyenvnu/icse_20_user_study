/** 
 * Get the current call stack trace usable for insertion into a {@link JTree}.
 * @param t the suspended thread to retrieve the call stack from
 * @return call stack as list of {@link DefaultMutableTreeNode}s
 */
protected List<DefaultMutableTreeNode> getStackTrace(ThreadReference t){
  List<DefaultMutableTreeNode> stack=new ArrayList<>();
  try {
    for (    StackFrame f : t.frames()) {
      stack.add(new DefaultMutableTreeNode(locationToString(f.location())));
    }
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
  }
  return stack;
}
