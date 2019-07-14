/** 
 * Compresses and writes  {@link #currentFrame} in a new StackMapTable entry. 
 */
private void putFrame(){
  final int numLocal=currentFrame[1];
  final int numStack=currentFrame[2];
  if (symbolTable.getMajorVersion() < Opcodes.V1_6) {
    stackMapTableEntries.putShort(currentFrame[0]).putShort(numLocal);
    putAbstractTypes(3,3 + numLocal);
    stackMapTableEntries.putShort(numStack);
    putAbstractTypes(3 + numLocal,3 + numLocal + numStack);
    return;
  }
  final int offsetDelta=stackMapTableNumberOfEntries == 0 ? currentFrame[0] : currentFrame[0] - previousFrame[0] - 1;
  final int previousNumlocal=previousFrame[1];
  final int numLocalDelta=numLocal - previousNumlocal;
  int type=Frame.FULL_FRAME;
  if (numStack == 0) {
switch (numLocalDelta) {
case -3:
case -2:
case -1:
      type=Frame.CHOP_FRAME;
    break;
case 0:
  type=offsetDelta < 64 ? Frame.SAME_FRAME : Frame.SAME_FRAME_EXTENDED;
break;
case 1:
case 2:
case 3:
type=Frame.APPEND_FRAME;
break;
default :
break;
}
}
 else if (numLocalDelta == 0 && numStack == 1) {
type=offsetDelta < 63 ? Frame.SAME_LOCALS_1_STACK_ITEM_FRAME : Frame.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED;
}
if (type != Frame.FULL_FRAME) {
int frameIndex=3;
for (int i=0; i < previousNumlocal && i < numLocal; i++) {
if (currentFrame[frameIndex] != previousFrame[frameIndex]) {
type=Frame.FULL_FRAME;
break;
}
frameIndex++;
}
}
switch (type) {
case Frame.SAME_FRAME:
stackMapTableEntries.putByte(offsetDelta);
break;
case Frame.SAME_LOCALS_1_STACK_ITEM_FRAME:
stackMapTableEntries.putByte(Frame.SAME_LOCALS_1_STACK_ITEM_FRAME + offsetDelta);
putAbstractTypes(3 + numLocal,4 + numLocal);
break;
case Frame.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED:
stackMapTableEntries.putByte(Frame.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED).putShort(offsetDelta);
putAbstractTypes(3 + numLocal,4 + numLocal);
break;
case Frame.SAME_FRAME_EXTENDED:
stackMapTableEntries.putByte(Frame.SAME_FRAME_EXTENDED).putShort(offsetDelta);
break;
case Frame.CHOP_FRAME:
stackMapTableEntries.putByte(Frame.SAME_FRAME_EXTENDED + numLocalDelta).putShort(offsetDelta);
break;
case Frame.APPEND_FRAME:
stackMapTableEntries.putByte(Frame.SAME_FRAME_EXTENDED + numLocalDelta).putShort(offsetDelta);
putAbstractTypes(3 + previousNumlocal,3 + numLocal);
break;
case Frame.FULL_FRAME:
default :
stackMapTableEntries.putByte(Frame.FULL_FRAME).putShort(offsetDelta).putShort(numLocal);
putAbstractTypes(3,3 + numLocal);
stackMapTableEntries.putShort(numStack);
putAbstractTypes(3 + numLocal,3 + numLocal + numStack);
break;
}
}
