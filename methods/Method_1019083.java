/** 
 * @param node Try-catch node.
 * @param method Method containing try-catch.
 * @return Text of try-catch block.
 */
public static TextHBox exception(TryCatchBlockNode node,MethodNode method){
  TextHBox t=new TextHBox();
  String type=node.type;
  if (type == null) {
    addRaw(t,"*");
  }
 else {
    addType(t,Type.getObjectType(node.type));
  }
  addRaw(t," - [");
  add(t,insnNode(node.start,method));
  addRaw(t," - ");
  add(t,insnNode(node.end,method));
  addRaw(t," # ");
  add(t,insnNode(node.handler,method));
  addRaw(t,"]");
  return t;
}
