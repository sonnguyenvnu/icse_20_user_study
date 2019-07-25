/** 
 * Reads an AbstractInsnNode from the given JsonObject
 * @param o Json object.
 * @return AbstractInsnNode instance.
 */
private static AbstractInsnNode parse(JsonObject o){
  String opcodeName=get(o,"opcode");
  int opcode=OpcodeUtil.nameToOpcode(opcodeName);
  int type=OpcodeUtil.opcodeToType(opcode);
switch (type) {
case AbstractInsnNode.INSN:
    return new InsnNode(opcode);
case AbstractInsnNode.FIELD_INSN:
  return new FieldInsnNode(opcode,get(o,"owner"),get(o,"name"),get(o,"desc"));
case AbstractInsnNode.METHOD_INSN:
return new MethodInsnNode(opcode,get(o,"owner"),get(o,"name"),get(o,"desc"),getBool(o,"itf"));
case AbstractInsnNode.IINC_INSN:
return new IincInsnNode(getInt(o,"var"),getInt(o,"incr"));
case AbstractInsnNode.VAR_INSN:
return new VarInsnNode(opcode,getInt(o,"var"));
case AbstractInsnNode.MULTIANEWARRAY_INSN:
return new MultiANewArrayInsnNode(get(o,"desc"),getInt(o,"dims"));
case AbstractInsnNode.INT_INSN:
return new IntInsnNode(opcode,getInt(o,"value"));
case AbstractInsnNode.LDC_INSN:
{
String typeHelper=get(o,"type-readonly");
Object obj=getObject(typeHelper,o);
return new LdcInsnNode(obj);
}
case AbstractInsnNode.TYPE_INSN:
return new TypeInsnNode(opcode,get(o,"desc"));
case AbstractInsnNode.LINE:
return new NamedLineNumberNode(getInt(o,"line"),null,get(o,"start"));
case AbstractInsnNode.LABEL:
return new NamedLabelNode(get(o,"id"));
case AbstractInsnNode.JUMP_INSN:
return new NamedJumpInsnNode(opcode,get(o,"dest"));
case AbstractInsnNode.TABLESWITCH_INSN:
return new NamedTableSwitchInsnNode(getInt(o,"min"),getInt(o,"max"),get(o,"default"),getStringArray(o,"labels"));
case AbstractInsnNode.LOOKUPSWITCH_INSN:
return new NamedLookupSwitchInsnNode(get(o,"default"),getStringArray(o,"labels"),getIntArray(o,"keys"));
case AbstractInsnNode.INVOKE_DYNAMIC_INSN:
JsonObject oHandle=o.get("handle").asObject();
Handle handle=(Handle)getObject("Handle",oHandle);
JsonArray oArr=o.get("args").asArray();
Object[] args=new Object[oArr.size()];
for (int i=0; i < args.length; i++) {
JsonObject oArg=oArr.get(i).asObject();
args[i]=getObject(get(oArg,"type-readonly"),oArg);
}
return new InvokeDynamicInsnNode(get(o,"name"),get(o,"desc"),handle,args);
}
throw new UnsupportedOperationException("Unsupported opcode <load> : " + opcodeName + " : " + opcode + "(type:" + type + ")");
}
