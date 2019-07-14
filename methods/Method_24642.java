/** 
 * Compile a list of current locals usable for insertion into a {@link JTree}. Recursively resolves object references.
 * @param t the suspended thread to get locals for
 * @param depth how deep to resolve nested object references. 0 will notresolve nested objects.
 * @return the list of current locals
 */
protected List<VariableNode> getLocals(ThreadReference t,int depth){
  List<VariableNode> vars=new ArrayList<>();
  try {
    if (t.frameCount() > 0) {
      StackFrame sf=t.frame(0);
      for (      LocalVariable lv : sf.visibleVariables()) {
        Value val=sf.getValue(lv);
        VariableNode var=new LocalVariableNode(lv.name(),lv.typeName(),val,lv,sf);
        if (depth > 0) {
          var.addChildren(getFields(val,depth - 1,true));
        }
        vars.add(var);
      }
    }
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
  }
catch (  AbsentInformationException ex) {
    loge("local variable information not available",ex);
  }
  return vars;
}
