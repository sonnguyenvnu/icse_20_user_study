/** 
 * @return formatted dump of the DFA Structure's
 */
public String dump(){
  StringBuilder stringDump=new StringBuilder(120).append("Data Flow Analysis Structure:\n").append("    Edge Nodes (ContinueBraceReturn) :");
  for (  StackObject stackObject : continueBreakReturnStack) {
    stringDump.append("\nCBR => ").append(stackObject.toString());
  }
  stringDump.append("\n    Scope Nodes:");
  for (  StackObject stackObject : braceStack) {
    stringDump.append("\nBraces => ").append(stackObject.toString());
  }
  return stringDump.toString();
}
