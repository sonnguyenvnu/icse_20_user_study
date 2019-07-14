/** 
 * Reads a JVMS 'Code' attribute and makes the given visitor visit it.
 * @param methodVisitor the visitor that must visit the Code attribute.
 * @param context information about the class being parsed.
 * @param codeOffset the start offset in {@link #classFileBuffer} of the Code attribute, excludingits attribute_name_index and attribute_length fields.
 */
private void readCode(final MethodVisitor methodVisitor,final Context context,final int codeOffset){
  int currentOffset=codeOffset;
  final byte[] classBuffer=classFileBuffer;
  final char[] charBuffer=context.charBuffer;
  final int maxStack=readUnsignedShort(currentOffset);
  final int maxLocals=readUnsignedShort(currentOffset + 2);
  final int codeLength=readInt(currentOffset + 4);
  currentOffset+=8;
  final int bytecodeStartOffset=currentOffset;
  final int bytecodeEndOffset=currentOffset + codeLength;
  final Label[] labels=context.currentMethodLabels=new Label[codeLength + 1];
  while (currentOffset < bytecodeEndOffset) {
    final int bytecodeOffset=currentOffset - bytecodeStartOffset;
    final int opcode=classBuffer[currentOffset] & 0xFF;
switch (opcode) {
case Constants.NOP:
case Constants.ACONST_NULL:
case Constants.ICONST_M1:
case Constants.ICONST_0:
case Constants.ICONST_1:
case Constants.ICONST_2:
case Constants.ICONST_3:
case Constants.ICONST_4:
case Constants.ICONST_5:
case Constants.LCONST_0:
case Constants.LCONST_1:
case Constants.FCONST_0:
case Constants.FCONST_1:
case Constants.FCONST_2:
case Constants.DCONST_0:
case Constants.DCONST_1:
case Constants.IALOAD:
case Constants.LALOAD:
case Constants.FALOAD:
case Constants.DALOAD:
case Constants.AALOAD:
case Constants.BALOAD:
case Constants.CALOAD:
case Constants.SALOAD:
case Constants.IASTORE:
case Constants.LASTORE:
case Constants.FASTORE:
case Constants.DASTORE:
case Constants.AASTORE:
case Constants.BASTORE:
case Constants.CASTORE:
case Constants.SASTORE:
case Constants.POP:
case Constants.POP2:
case Constants.DUP:
case Constants.DUP_X1:
case Constants.DUP_X2:
case Constants.DUP2:
case Constants.DUP2_X1:
case Constants.DUP2_X2:
case Constants.SWAP:
case Constants.IADD:
case Constants.LADD:
case Constants.FADD:
case Constants.DADD:
case Constants.ISUB:
case Constants.LSUB:
case Constants.FSUB:
case Constants.DSUB:
case Constants.IMUL:
case Constants.LMUL:
case Constants.FMUL:
case Constants.DMUL:
case Constants.IDIV:
case Constants.LDIV:
case Constants.FDIV:
case Constants.DDIV:
case Constants.IREM:
case Constants.LREM:
case Constants.FREM:
case Constants.DREM:
case Constants.INEG:
case Constants.LNEG:
case Constants.FNEG:
case Constants.DNEG:
case Constants.ISHL:
case Constants.LSHL:
case Constants.ISHR:
case Constants.LSHR:
case Constants.IUSHR:
case Constants.LUSHR:
case Constants.IAND:
case Constants.LAND:
case Constants.IOR:
case Constants.LOR:
case Constants.IXOR:
case Constants.LXOR:
case Constants.I2L:
case Constants.I2F:
case Constants.I2D:
case Constants.L2I:
case Constants.L2F:
case Constants.L2D:
case Constants.F2I:
case Constants.F2L:
case Constants.F2D:
case Constants.D2I:
case Constants.D2L:
case Constants.D2F:
case Constants.I2B:
case Constants.I2C:
case Constants.I2S:
case Constants.LCMP:
case Constants.FCMPL:
case Constants.FCMPG:
case Constants.DCMPL:
case Constants.DCMPG:
case Constants.IRETURN:
case Constants.LRETURN:
case Constants.FRETURN:
case Constants.DRETURN:
case Constants.ARETURN:
case Constants.RETURN:
case Constants.ARRAYLENGTH:
case Constants.ATHROW:
case Constants.MONITORENTER:
case Constants.MONITOREXIT:
case Constants.ILOAD_0:
case Constants.ILOAD_1:
case Constants.ILOAD_2:
case Constants.ILOAD_3:
case Constants.LLOAD_0:
case Constants.LLOAD_1:
case Constants.LLOAD_2:
case Constants.LLOAD_3:
case Constants.FLOAD_0:
case Constants.FLOAD_1:
case Constants.FLOAD_2:
case Constants.FLOAD_3:
case Constants.DLOAD_0:
case Constants.DLOAD_1:
case Constants.DLOAD_2:
case Constants.DLOAD_3:
case Constants.ALOAD_0:
case Constants.ALOAD_1:
case Constants.ALOAD_2:
case Constants.ALOAD_3:
case Constants.ISTORE_0:
case Constants.ISTORE_1:
case Constants.ISTORE_2:
case Constants.ISTORE_3:
case Constants.LSTORE_0:
case Constants.LSTORE_1:
case Constants.LSTORE_2:
case Constants.LSTORE_3:
case Constants.FSTORE_0:
case Constants.FSTORE_1:
case Constants.FSTORE_2:
case Constants.FSTORE_3:
case Constants.DSTORE_0:
case Constants.DSTORE_1:
case Constants.DSTORE_2:
case Constants.DSTORE_3:
case Constants.ASTORE_0:
case Constants.ASTORE_1:
case Constants.ASTORE_2:
case Constants.ASTORE_3:
      currentOffset+=1;
    break;
case Constants.IFEQ:
case Constants.IFNE:
case Constants.IFLT:
case Constants.IFGE:
case Constants.IFGT:
case Constants.IFLE:
case Constants.IF_ICMPEQ:
case Constants.IF_ICMPNE:
case Constants.IF_ICMPLT:
case Constants.IF_ICMPGE:
case Constants.IF_ICMPGT:
case Constants.IF_ICMPLE:
case Constants.IF_ACMPEQ:
case Constants.IF_ACMPNE:
case Constants.GOTO:
case Constants.JSR:
case Constants.IFNULL:
case Constants.IFNONNULL:
  createLabel(bytecodeOffset + readShort(currentOffset + 1),labels);
currentOffset+=3;
break;
case Constants.ASM_IFEQ:
case Constants.ASM_IFNE:
case Constants.ASM_IFLT:
case Constants.ASM_IFGE:
case Constants.ASM_IFGT:
case Constants.ASM_IFLE:
case Constants.ASM_IF_ICMPEQ:
case Constants.ASM_IF_ICMPNE:
case Constants.ASM_IF_ICMPLT:
case Constants.ASM_IF_ICMPGE:
case Constants.ASM_IF_ICMPGT:
case Constants.ASM_IF_ICMPLE:
case Constants.ASM_IF_ACMPEQ:
case Constants.ASM_IF_ACMPNE:
case Constants.ASM_GOTO:
case Constants.ASM_JSR:
case Constants.ASM_IFNULL:
case Constants.ASM_IFNONNULL:
createLabel(bytecodeOffset + readUnsignedShort(currentOffset + 1),labels);
currentOffset+=3;
break;
case Constants.GOTO_W:
case Constants.JSR_W:
case Constants.ASM_GOTO_W:
createLabel(bytecodeOffset + readInt(currentOffset + 1),labels);
currentOffset+=5;
break;
case Constants.WIDE:
switch (classBuffer[currentOffset + 1] & 0xFF) {
case Constants.ILOAD:
case Constants.FLOAD:
case Constants.ALOAD:
case Constants.LLOAD:
case Constants.DLOAD:
case Constants.ISTORE:
case Constants.FSTORE:
case Constants.ASTORE:
case Constants.LSTORE:
case Constants.DSTORE:
case Constants.RET:
currentOffset+=4;
break;
case Constants.IINC:
currentOffset+=6;
break;
default :
throw new IllegalArgumentException();
}
break;
case Constants.TABLESWITCH:
currentOffset+=4 - (bytecodeOffset & 3);
createLabel(bytecodeOffset + readInt(currentOffset),labels);
int numTableEntries=readInt(currentOffset + 8) - readInt(currentOffset + 4) + 1;
currentOffset+=12;
while (numTableEntries-- > 0) {
createLabel(bytecodeOffset + readInt(currentOffset),labels);
currentOffset+=4;
}
break;
case Constants.LOOKUPSWITCH:
currentOffset+=4 - (bytecodeOffset & 3);
createLabel(bytecodeOffset + readInt(currentOffset),labels);
int numSwitchCases=readInt(currentOffset + 4);
currentOffset+=8;
while (numSwitchCases-- > 0) {
createLabel(bytecodeOffset + readInt(currentOffset + 4),labels);
currentOffset+=8;
}
break;
case Constants.ILOAD:
case Constants.LLOAD:
case Constants.FLOAD:
case Constants.DLOAD:
case Constants.ALOAD:
case Constants.ISTORE:
case Constants.LSTORE:
case Constants.FSTORE:
case Constants.DSTORE:
case Constants.ASTORE:
case Constants.RET:
case Constants.BIPUSH:
case Constants.NEWARRAY:
case Constants.LDC:
currentOffset+=2;
break;
case Constants.SIPUSH:
case Constants.LDC_W:
case Constants.LDC2_W:
case Constants.GETSTATIC:
case Constants.PUTSTATIC:
case Constants.GETFIELD:
case Constants.PUTFIELD:
case Constants.INVOKEVIRTUAL:
case Constants.INVOKESPECIAL:
case Constants.INVOKESTATIC:
case Constants.NEW:
case Constants.ANEWARRAY:
case Constants.CHECKCAST:
case Constants.INSTANCEOF:
case Constants.IINC:
currentOffset+=3;
break;
case Constants.INVOKEINTERFACE:
case Constants.INVOKEDYNAMIC:
currentOffset+=5;
break;
case Constants.MULTIANEWARRAY:
currentOffset+=4;
break;
default :
throw new IllegalArgumentException();
}
}
int exceptionTableLength=readUnsignedShort(currentOffset);
currentOffset+=2;
while (exceptionTableLength-- > 0) {
Label start=createLabel(readUnsignedShort(currentOffset),labels);
Label end=createLabel(readUnsignedShort(currentOffset + 2),labels);
Label handler=createLabel(readUnsignedShort(currentOffset + 4),labels);
String catchType=readUTF8(cpInfoOffsets[readUnsignedShort(currentOffset + 6)],charBuffer);
currentOffset+=8;
methodVisitor.visitTryCatchBlock(start,end,handler,catchType);
}
int stackMapFrameOffset=0;
int stackMapTableEndOffset=0;
boolean compressedFrames=true;
int localVariableTableOffset=0;
int localVariableTypeTableOffset=0;
int[] visibleTypeAnnotationOffsets=null;
int[] invisibleTypeAnnotationOffsets=null;
Attribute attributes=null;
int attributesCount=readUnsignedShort(currentOffset);
currentOffset+=2;
while (attributesCount-- > 0) {
String attributeName=readUTF8(currentOffset,charBuffer);
int attributeLength=readInt(currentOffset + 2);
currentOffset+=6;
if (Constants.LOCAL_VARIABLE_TABLE.equals(attributeName)) {
if ((context.parsingOptions & SKIP_DEBUG) == 0) {
localVariableTableOffset=currentOffset;
int currentLocalVariableTableOffset=currentOffset;
int localVariableTableLength=readUnsignedShort(currentLocalVariableTableOffset);
currentLocalVariableTableOffset+=2;
while (localVariableTableLength-- > 0) {
int startPc=readUnsignedShort(currentLocalVariableTableOffset);
createDebugLabel(startPc,labels);
int length=readUnsignedShort(currentLocalVariableTableOffset + 2);
createDebugLabel(startPc + length,labels);
currentLocalVariableTableOffset+=10;
}
}
}
 else if (Constants.LOCAL_VARIABLE_TYPE_TABLE.equals(attributeName)) {
localVariableTypeTableOffset=currentOffset;
}
 else if (Constants.LINE_NUMBER_TABLE.equals(attributeName)) {
if ((context.parsingOptions & SKIP_DEBUG) == 0) {
int currentLineNumberTableOffset=currentOffset;
int lineNumberTableLength=readUnsignedShort(currentLineNumberTableOffset);
currentLineNumberTableOffset+=2;
while (lineNumberTableLength-- > 0) {
int startPc=readUnsignedShort(currentLineNumberTableOffset);
int lineNumber=readUnsignedShort(currentLineNumberTableOffset + 2);
currentLineNumberTableOffset+=4;
createDebugLabel(startPc,labels);
labels[startPc].addLineNumber(lineNumber);
}
}
}
 else if (Constants.RUNTIME_VISIBLE_TYPE_ANNOTATIONS.equals(attributeName)) {
visibleTypeAnnotationOffsets=readTypeAnnotations(methodVisitor,context,currentOffset,true);
}
 else if (Constants.RUNTIME_INVISIBLE_TYPE_ANNOTATIONS.equals(attributeName)) {
invisibleTypeAnnotationOffsets=readTypeAnnotations(methodVisitor,context,currentOffset,false);
}
 else if (Constants.STACK_MAP_TABLE.equals(attributeName)) {
if ((context.parsingOptions & SKIP_FRAMES) == 0) {
stackMapFrameOffset=currentOffset + 2;
stackMapTableEndOffset=currentOffset + attributeLength;
}
}
 else if ("StackMap".equals(attributeName)) {
if ((context.parsingOptions & SKIP_FRAMES) == 0) {
stackMapFrameOffset=currentOffset + 2;
stackMapTableEndOffset=currentOffset + attributeLength;
compressedFrames=false;
}
}
 else {
Attribute attribute=readAttribute(context.attributePrototypes,attributeName,currentOffset,attributeLength,charBuffer,codeOffset,labels);
attribute.nextAttribute=attributes;
attributes=attribute;
}
currentOffset+=attributeLength;
}
final boolean expandFrames=(context.parsingOptions & EXPAND_FRAMES) != 0;
if (stackMapFrameOffset != 0) {
context.currentFrameOffset=-1;
context.currentFrameType=0;
context.currentFrameLocalCount=0;
context.currentFrameLocalCountDelta=0;
context.currentFrameLocalTypes=new Object[maxLocals];
context.currentFrameStackCount=0;
context.currentFrameStackTypes=new Object[maxStack];
if (expandFrames) {
computeImplicitFrame(context);
}
for (int offset=stackMapFrameOffset; offset < stackMapTableEndOffset - 2; ++offset) {
if (classBuffer[offset] == Frame.ITEM_UNINITIALIZED) {
int potentialBytecodeOffset=readUnsignedShort(offset + 1);
if (potentialBytecodeOffset >= 0 && potentialBytecodeOffset < codeLength && (classBuffer[bytecodeStartOffset + potentialBytecodeOffset] & 0xFF) == Opcodes.NEW) {
createLabel(potentialBytecodeOffset,labels);
}
}
}
}
if (expandFrames && (context.parsingOptions & EXPAND_ASM_INSNS) != 0) {
methodVisitor.visitFrame(Opcodes.F_NEW,maxLocals,null,0,null);
}
int currentVisibleTypeAnnotationIndex=0;
int currentVisibleTypeAnnotationBytecodeOffset=getTypeAnnotationBytecodeOffset(visibleTypeAnnotationOffsets,0);
int currentInvisibleTypeAnnotationIndex=0;
int currentInvisibleTypeAnnotationBytecodeOffset=getTypeAnnotationBytecodeOffset(invisibleTypeAnnotationOffsets,0);
boolean insertFrame=false;
final int wideJumpOpcodeDelta=(context.parsingOptions & EXPAND_ASM_INSNS) == 0 ? Constants.WIDE_JUMP_OPCODE_DELTA : 0;
currentOffset=bytecodeStartOffset;
while (currentOffset < bytecodeEndOffset) {
final int currentBytecodeOffset=currentOffset - bytecodeStartOffset;
Label currentLabel=labels[currentBytecodeOffset];
if (currentLabel != null) {
currentLabel.accept(methodVisitor,(context.parsingOptions & SKIP_DEBUG) == 0);
}
while (stackMapFrameOffset != 0 && (context.currentFrameOffset == currentBytecodeOffset || context.currentFrameOffset == -1)) {
if (context.currentFrameOffset != -1) {
if (!compressedFrames || expandFrames) {
methodVisitor.visitFrame(Opcodes.F_NEW,context.currentFrameLocalCount,context.currentFrameLocalTypes,context.currentFrameStackCount,context.currentFrameStackTypes);
}
 else {
methodVisitor.visitFrame(context.currentFrameType,context.currentFrameLocalCountDelta,context.currentFrameLocalTypes,context.currentFrameStackCount,context.currentFrameStackTypes);
}
insertFrame=false;
}
if (stackMapFrameOffset < stackMapTableEndOffset) {
stackMapFrameOffset=readStackMapFrame(stackMapFrameOffset,compressedFrames,expandFrames,context);
}
 else {
stackMapFrameOffset=0;
}
}
if (insertFrame) {
if ((context.parsingOptions & EXPAND_FRAMES) != 0) {
methodVisitor.visitFrame(Constants.F_INSERT,0,null,0,null);
}
insertFrame=false;
}
int opcode=classBuffer[currentOffset] & 0xFF;
switch (opcode) {
case Constants.NOP:
case Constants.ACONST_NULL:
case Constants.ICONST_M1:
case Constants.ICONST_0:
case Constants.ICONST_1:
case Constants.ICONST_2:
case Constants.ICONST_3:
case Constants.ICONST_4:
case Constants.ICONST_5:
case Constants.LCONST_0:
case Constants.LCONST_1:
case Constants.FCONST_0:
case Constants.FCONST_1:
case Constants.FCONST_2:
case Constants.DCONST_0:
case Constants.DCONST_1:
case Constants.IALOAD:
case Constants.LALOAD:
case Constants.FALOAD:
case Constants.DALOAD:
case Constants.AALOAD:
case Constants.BALOAD:
case Constants.CALOAD:
case Constants.SALOAD:
case Constants.IASTORE:
case Constants.LASTORE:
case Constants.FASTORE:
case Constants.DASTORE:
case Constants.AASTORE:
case Constants.BASTORE:
case Constants.CASTORE:
case Constants.SASTORE:
case Constants.POP:
case Constants.POP2:
case Constants.DUP:
case Constants.DUP_X1:
case Constants.DUP_X2:
case Constants.DUP2:
case Constants.DUP2_X1:
case Constants.DUP2_X2:
case Constants.SWAP:
case Constants.IADD:
case Constants.LADD:
case Constants.FADD:
case Constants.DADD:
case Constants.ISUB:
case Constants.LSUB:
case Constants.FSUB:
case Constants.DSUB:
case Constants.IMUL:
case Constants.LMUL:
case Constants.FMUL:
case Constants.DMUL:
case Constants.IDIV:
case Constants.LDIV:
case Constants.FDIV:
case Constants.DDIV:
case Constants.IREM:
case Constants.LREM:
case Constants.FREM:
case Constants.DREM:
case Constants.INEG:
case Constants.LNEG:
case Constants.FNEG:
case Constants.DNEG:
case Constants.ISHL:
case Constants.LSHL:
case Constants.ISHR:
case Constants.LSHR:
case Constants.IUSHR:
case Constants.LUSHR:
case Constants.IAND:
case Constants.LAND:
case Constants.IOR:
case Constants.LOR:
case Constants.IXOR:
case Constants.LXOR:
case Constants.I2L:
case Constants.I2F:
case Constants.I2D:
case Constants.L2I:
case Constants.L2F:
case Constants.L2D:
case Constants.F2I:
case Constants.F2L:
case Constants.F2D:
case Constants.D2I:
case Constants.D2L:
case Constants.D2F:
case Constants.I2B:
case Constants.I2C:
case Constants.I2S:
case Constants.LCMP:
case Constants.FCMPL:
case Constants.FCMPG:
case Constants.DCMPL:
case Constants.DCMPG:
case Constants.IRETURN:
case Constants.LRETURN:
case Constants.FRETURN:
case Constants.DRETURN:
case Constants.ARETURN:
case Constants.RETURN:
case Constants.ARRAYLENGTH:
case Constants.ATHROW:
case Constants.MONITORENTER:
case Constants.MONITOREXIT:
methodVisitor.visitInsn(opcode);
currentOffset+=1;
break;
case Constants.ILOAD_0:
case Constants.ILOAD_1:
case Constants.ILOAD_2:
case Constants.ILOAD_3:
case Constants.LLOAD_0:
case Constants.LLOAD_1:
case Constants.LLOAD_2:
case Constants.LLOAD_3:
case Constants.FLOAD_0:
case Constants.FLOAD_1:
case Constants.FLOAD_2:
case Constants.FLOAD_3:
case Constants.DLOAD_0:
case Constants.DLOAD_1:
case Constants.DLOAD_2:
case Constants.DLOAD_3:
case Constants.ALOAD_0:
case Constants.ALOAD_1:
case Constants.ALOAD_2:
case Constants.ALOAD_3:
opcode-=Constants.ILOAD_0;
methodVisitor.visitVarInsn(Opcodes.ILOAD + (opcode >> 2),opcode & 0x3);
currentOffset+=1;
break;
case Constants.ISTORE_0:
case Constants.ISTORE_1:
case Constants.ISTORE_2:
case Constants.ISTORE_3:
case Constants.LSTORE_0:
case Constants.LSTORE_1:
case Constants.LSTORE_2:
case Constants.LSTORE_3:
case Constants.FSTORE_0:
case Constants.FSTORE_1:
case Constants.FSTORE_2:
case Constants.FSTORE_3:
case Constants.DSTORE_0:
case Constants.DSTORE_1:
case Constants.DSTORE_2:
case Constants.DSTORE_3:
case Constants.ASTORE_0:
case Constants.ASTORE_1:
case Constants.ASTORE_2:
case Constants.ASTORE_3:
opcode-=Constants.ISTORE_0;
methodVisitor.visitVarInsn(Opcodes.ISTORE + (opcode >> 2),opcode & 0x3);
currentOffset+=1;
break;
case Constants.IFEQ:
case Constants.IFNE:
case Constants.IFLT:
case Constants.IFGE:
case Constants.IFGT:
case Constants.IFLE:
case Constants.IF_ICMPEQ:
case Constants.IF_ICMPNE:
case Constants.IF_ICMPLT:
case Constants.IF_ICMPGE:
case Constants.IF_ICMPGT:
case Constants.IF_ICMPLE:
case Constants.IF_ACMPEQ:
case Constants.IF_ACMPNE:
case Constants.GOTO:
case Constants.JSR:
case Constants.IFNULL:
case Constants.IFNONNULL:
methodVisitor.visitJumpInsn(opcode,labels[currentBytecodeOffset + readShort(currentOffset + 1)]);
currentOffset+=3;
break;
case Constants.GOTO_W:
case Constants.JSR_W:
methodVisitor.visitJumpInsn(opcode - wideJumpOpcodeDelta,labels[currentBytecodeOffset + readInt(currentOffset + 1)]);
currentOffset+=5;
break;
case Constants.ASM_IFEQ:
case Constants.ASM_IFNE:
case Constants.ASM_IFLT:
case Constants.ASM_IFGE:
case Constants.ASM_IFGT:
case Constants.ASM_IFLE:
case Constants.ASM_IF_ICMPEQ:
case Constants.ASM_IF_ICMPNE:
case Constants.ASM_IF_ICMPLT:
case Constants.ASM_IF_ICMPGE:
case Constants.ASM_IF_ICMPGT:
case Constants.ASM_IF_ICMPLE:
case Constants.ASM_IF_ACMPEQ:
case Constants.ASM_IF_ACMPNE:
case Constants.ASM_GOTO:
case Constants.ASM_JSR:
case Constants.ASM_IFNULL:
case Constants.ASM_IFNONNULL:
{
opcode=opcode < Constants.ASM_IFNULL ? opcode - Constants.ASM_OPCODE_DELTA : opcode - Constants.ASM_IFNULL_OPCODE_DELTA;
Label target=labels[currentBytecodeOffset + readUnsignedShort(currentOffset + 1)];
if (opcode == Opcodes.GOTO || opcode == Opcodes.JSR) {
methodVisitor.visitJumpInsn(opcode + Constants.WIDE_JUMP_OPCODE_DELTA,target);
}
 else {
opcode=opcode < Opcodes.GOTO ? ((opcode + 1) ^ 1) - 1 : opcode ^ 1;
Label endif=createLabel(currentBytecodeOffset + 3,labels);
methodVisitor.visitJumpInsn(opcode,endif);
methodVisitor.visitJumpInsn(Constants.GOTO_W,target);
insertFrame=true;
}
currentOffset+=3;
break;
}
case Constants.ASM_GOTO_W:
methodVisitor.visitJumpInsn(Constants.GOTO_W,labels[currentBytecodeOffset + readInt(currentOffset + 1)]);
insertFrame=true;
currentOffset+=5;
break;
case Constants.WIDE:
opcode=classBuffer[currentOffset + 1] & 0xFF;
if (opcode == Opcodes.IINC) {
methodVisitor.visitIincInsn(readUnsignedShort(currentOffset + 2),readShort(currentOffset + 4));
currentOffset+=6;
}
 else {
methodVisitor.visitVarInsn(opcode,readUnsignedShort(currentOffset + 2));
currentOffset+=4;
}
break;
case Constants.TABLESWITCH:
{
currentOffset+=4 - (currentBytecodeOffset & 3);
Label defaultLabel=labels[currentBytecodeOffset + readInt(currentOffset)];
int low=readInt(currentOffset + 4);
int high=readInt(currentOffset + 8);
currentOffset+=12;
Label[] table=new Label[high - low + 1];
for (int i=0; i < table.length; ++i) {
table[i]=labels[currentBytecodeOffset + readInt(currentOffset)];
currentOffset+=4;
}
methodVisitor.visitTableSwitchInsn(low,high,defaultLabel,table);
break;
}
case Constants.LOOKUPSWITCH:
{
currentOffset+=4 - (currentBytecodeOffset & 3);
Label defaultLabel=labels[currentBytecodeOffset + readInt(currentOffset)];
int numPairs=readInt(currentOffset + 4);
currentOffset+=8;
int[] keys=new int[numPairs];
Label[] values=new Label[numPairs];
for (int i=0; i < numPairs; ++i) {
keys[i]=readInt(currentOffset);
values[i]=labels[currentBytecodeOffset + readInt(currentOffset + 4)];
currentOffset+=8;
}
methodVisitor.visitLookupSwitchInsn(defaultLabel,keys,values);
break;
}
case Constants.ILOAD:
case Constants.LLOAD:
case Constants.FLOAD:
case Constants.DLOAD:
case Constants.ALOAD:
case Constants.ISTORE:
case Constants.LSTORE:
case Constants.FSTORE:
case Constants.DSTORE:
case Constants.ASTORE:
case Constants.RET:
methodVisitor.visitVarInsn(opcode,classBuffer[currentOffset + 1] & 0xFF);
currentOffset+=2;
break;
case Constants.BIPUSH:
case Constants.NEWARRAY:
methodVisitor.visitIntInsn(opcode,classBuffer[currentOffset + 1]);
currentOffset+=2;
break;
case Constants.SIPUSH:
methodVisitor.visitIntInsn(opcode,readShort(currentOffset + 1));
currentOffset+=3;
break;
case Constants.LDC:
methodVisitor.visitLdcInsn(readConst(classBuffer[currentOffset + 1] & 0xFF,charBuffer));
currentOffset+=2;
break;
case Constants.LDC_W:
case Constants.LDC2_W:
methodVisitor.visitLdcInsn(readConst(readUnsignedShort(currentOffset + 1),charBuffer));
currentOffset+=3;
break;
case Constants.GETSTATIC:
case Constants.PUTSTATIC:
case Constants.GETFIELD:
case Constants.PUTFIELD:
case Constants.INVOKEVIRTUAL:
case Constants.INVOKESPECIAL:
case Constants.INVOKESTATIC:
case Constants.INVOKEINTERFACE:
{
int cpInfoOffset=cpInfoOffsets[readUnsignedShort(currentOffset + 1)];
int nameAndTypeCpInfoOffset=cpInfoOffsets[readUnsignedShort(cpInfoOffset + 2)];
String owner=readClass(cpInfoOffset,charBuffer);
String name=readUTF8(nameAndTypeCpInfoOffset,charBuffer);
String descriptor=readUTF8(nameAndTypeCpInfoOffset + 2,charBuffer);
if (opcode < Opcodes.INVOKEVIRTUAL) {
methodVisitor.visitFieldInsn(opcode,owner,name,descriptor);
}
 else {
boolean isInterface=classBuffer[cpInfoOffset - 1] == Symbol.CONSTANT_INTERFACE_METHODREF_TAG;
methodVisitor.visitMethodInsn(opcode,owner,name,descriptor,isInterface);
}
if (opcode == Opcodes.INVOKEINTERFACE) {
currentOffset+=5;
}
 else {
currentOffset+=3;
}
break;
}
case Constants.INVOKEDYNAMIC:
{
int cpInfoOffset=cpInfoOffsets[readUnsignedShort(currentOffset + 1)];
int nameAndTypeCpInfoOffset=cpInfoOffsets[readUnsignedShort(cpInfoOffset + 2)];
String name=readUTF8(nameAndTypeCpInfoOffset,charBuffer);
String descriptor=readUTF8(nameAndTypeCpInfoOffset + 2,charBuffer);
int bootstrapMethodOffset=bootstrapMethodOffsets[readUnsignedShort(cpInfoOffset)];
Handle handle=(Handle)readConst(readUnsignedShort(bootstrapMethodOffset),charBuffer);
Object[] bootstrapMethodArguments=new Object[readUnsignedShort(bootstrapMethodOffset + 2)];
bootstrapMethodOffset+=4;
for (int i=0; i < bootstrapMethodArguments.length; i++) {
bootstrapMethodArguments[i]=readConst(readUnsignedShort(bootstrapMethodOffset),charBuffer);
bootstrapMethodOffset+=2;
}
methodVisitor.visitInvokeDynamicInsn(name,descriptor,handle,bootstrapMethodArguments);
currentOffset+=5;
break;
}
case Constants.NEW:
case Constants.ANEWARRAY:
case Constants.CHECKCAST:
case Constants.INSTANCEOF:
methodVisitor.visitTypeInsn(opcode,readClass(currentOffset + 1,charBuffer));
currentOffset+=3;
break;
case Constants.IINC:
methodVisitor.visitIincInsn(classBuffer[currentOffset + 1] & 0xFF,classBuffer[currentOffset + 2]);
currentOffset+=3;
break;
case Constants.MULTIANEWARRAY:
methodVisitor.visitMultiANewArrayInsn(readClass(currentOffset + 1,charBuffer),classBuffer[currentOffset + 3] & 0xFF);
currentOffset+=4;
break;
default :
throw new AssertionError();
}
while (visibleTypeAnnotationOffsets != null && currentVisibleTypeAnnotationIndex < visibleTypeAnnotationOffsets.length && currentVisibleTypeAnnotationBytecodeOffset <= currentBytecodeOffset) {
if (currentVisibleTypeAnnotationBytecodeOffset == currentBytecodeOffset) {
int currentAnnotationOffset=readTypeAnnotationTarget(context,visibleTypeAnnotationOffsets[currentVisibleTypeAnnotationIndex]);
String annotationDescriptor=readUTF8(currentAnnotationOffset,charBuffer);
currentAnnotationOffset+=2;
readElementValues(methodVisitor.visitInsnAnnotation(context.currentTypeAnnotationTarget,context.currentTypeAnnotationTargetPath,annotationDescriptor,true),currentAnnotationOffset,true,charBuffer);
}
currentVisibleTypeAnnotationBytecodeOffset=getTypeAnnotationBytecodeOffset(visibleTypeAnnotationOffsets,++currentVisibleTypeAnnotationIndex);
}
while (invisibleTypeAnnotationOffsets != null && currentInvisibleTypeAnnotationIndex < invisibleTypeAnnotationOffsets.length && currentInvisibleTypeAnnotationBytecodeOffset <= currentBytecodeOffset) {
if (currentInvisibleTypeAnnotationBytecodeOffset == currentBytecodeOffset) {
int currentAnnotationOffset=readTypeAnnotationTarget(context,invisibleTypeAnnotationOffsets[currentInvisibleTypeAnnotationIndex]);
String annotationDescriptor=readUTF8(currentAnnotationOffset,charBuffer);
currentAnnotationOffset+=2;
readElementValues(methodVisitor.visitInsnAnnotation(context.currentTypeAnnotationTarget,context.currentTypeAnnotationTargetPath,annotationDescriptor,false),currentAnnotationOffset,true,charBuffer);
}
currentInvisibleTypeAnnotationBytecodeOffset=getTypeAnnotationBytecodeOffset(invisibleTypeAnnotationOffsets,++currentInvisibleTypeAnnotationIndex);
}
}
if (labels[codeLength] != null) {
methodVisitor.visitLabel(labels[codeLength]);
}
if (localVariableTableOffset != 0 && (context.parsingOptions & SKIP_DEBUG) == 0) {
int[] typeTable=null;
if (localVariableTypeTableOffset != 0) {
typeTable=new int[readUnsignedShort(localVariableTypeTableOffset) * 3];
currentOffset=localVariableTypeTableOffset + 2;
int typeTableIndex=typeTable.length;
while (typeTableIndex > 0) {
typeTable[--typeTableIndex]=currentOffset + 6;
typeTable[--typeTableIndex]=readUnsignedShort(currentOffset + 8);
typeTable[--typeTableIndex]=readUnsignedShort(currentOffset);
currentOffset+=10;
}
}
int localVariableTableLength=readUnsignedShort(localVariableTableOffset);
currentOffset=localVariableTableOffset + 2;
while (localVariableTableLength-- > 0) {
int startPc=readUnsignedShort(currentOffset);
int length=readUnsignedShort(currentOffset + 2);
String name=readUTF8(currentOffset + 4,charBuffer);
String descriptor=readUTF8(currentOffset + 6,charBuffer);
int index=readUnsignedShort(currentOffset + 8);
currentOffset+=10;
String signature=null;
if (typeTable != null) {
for (int i=0; i < typeTable.length; i+=3) {
if (typeTable[i] == startPc && typeTable[i + 1] == index) {
signature=readUTF8(typeTable[i + 2],charBuffer);
break;
}
}
}
methodVisitor.visitLocalVariable(name,descriptor,signature,labels[startPc],labels[startPc + length],index);
}
}
if (visibleTypeAnnotationOffsets != null) {
for (int typeAnnotationOffset : visibleTypeAnnotationOffsets) {
int targetType=readByte(typeAnnotationOffset);
if (targetType == TypeReference.LOCAL_VARIABLE || targetType == TypeReference.RESOURCE_VARIABLE) {
currentOffset=readTypeAnnotationTarget(context,typeAnnotationOffset);
String annotationDescriptor=readUTF8(currentOffset,charBuffer);
currentOffset+=2;
readElementValues(methodVisitor.visitLocalVariableAnnotation(context.currentTypeAnnotationTarget,context.currentTypeAnnotationTargetPath,context.currentLocalVariableAnnotationRangeStarts,context.currentLocalVariableAnnotationRangeEnds,context.currentLocalVariableAnnotationRangeIndices,annotationDescriptor,true),currentOffset,true,charBuffer);
}
}
}
if (invisibleTypeAnnotationOffsets != null) {
for (int typeAnnotationOffset : invisibleTypeAnnotationOffsets) {
int targetType=readByte(typeAnnotationOffset);
if (targetType == TypeReference.LOCAL_VARIABLE || targetType == TypeReference.RESOURCE_VARIABLE) {
currentOffset=readTypeAnnotationTarget(context,typeAnnotationOffset);
String annotationDescriptor=readUTF8(currentOffset,charBuffer);
currentOffset+=2;
readElementValues(methodVisitor.visitLocalVariableAnnotation(context.currentTypeAnnotationTarget,context.currentTypeAnnotationTargetPath,context.currentLocalVariableAnnotationRangeStarts,context.currentLocalVariableAnnotationRangeEnds,context.currentLocalVariableAnnotationRangeIndices,annotationDescriptor,false),currentOffset,true,charBuffer);
}
}
}
while (attributes != null) {
Attribute nextAttribute=attributes.nextAttribute;
attributes.nextAttribute=null;
methodVisitor.visitAttribute(attributes);
attributes=nextAttribute;
}
methodVisitor.visitMaxs(maxStack,maxLocals);
}
