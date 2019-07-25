private int replace(ConstPool cp,CodeIterator iterator,int pos,int opcode,String signature) throws BadBytecode {
  String castType=null;
  String methodName=getMethodName(opcode);
  if (methodName != null) {
    if (opcode == AALOAD) {
      castType=getTopType(iterator.lookAhead());
      if (castType == null)       return pos;
      if ("java/lang/Object".equals(castType))       castType=null;
    }
    iterator.writeByte(NOP,pos);
    CodeIterator.Gap gap=iterator.insertGapAt(pos,castType != null ? 5 : 2,false);
    pos=gap.position;
    int mi=cp.addClassInfo(methodClassname);
    int methodref=cp.addMethodrefInfo(mi,methodName,signature);
    iterator.writeByte(INVOKESTATIC,pos);
    iterator.write16bit(methodref,pos + 1);
    if (castType != null) {
      int index=cp.addClassInfo(castType);
      iterator.writeByte(CHECKCAST,pos + 3);
      iterator.write16bit(index,pos + 4);
    }
    pos=updatePos(pos,gap.length);
  }
  return pos;
}
