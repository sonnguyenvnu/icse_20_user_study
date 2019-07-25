/** 
 * Text representation of a local variable.
 * @param node Local variable.
 * @param method Method containing the variable.
 * @return
 */
public static TextHBox variable(LocalVariableNode node,MethodNode method){
  TextHBox t=new TextHBox();
  addName(t,node.name);
  addRaw(t," - ");
  addType(t,Type.getType(node.desc));
  return t;
}
