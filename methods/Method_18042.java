/** 
 * @return the input node for the given input name
 */
protected ValueNode getInput(String name){
  final ValueNode input=getInputUnsafe(name);
  if (input == null) {
    throw new RuntimeException("Tried to get non-existent input '" + name + "'. Node only has these inputs: " + buildDebugInputsString());
  }
  return input;
}
