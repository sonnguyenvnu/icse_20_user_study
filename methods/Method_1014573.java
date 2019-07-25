/** 
 * Target can be a memory location or register adressable by dst[dstIndex] FamilyOpcode is the value encoded in the source operand as immidiate value it will be used to determince what specfic SETcc operation should be execute
 */
@Override public Status execute(Target dst,int dstIndex,int familyOpCode,Status status){
  return setcc(dst,dstIndex,familyOpCode,status);
}
