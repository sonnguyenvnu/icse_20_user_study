/** 
 * Reads a JVMS 'stack_map_frame' structure and stores the result in the given  {@link Context}object. This method can also be used to read a full_frame structure, excluding its frame_type field (this is used to parse the legacy StackMap attributes).
 * @param stackMapFrameOffset the start offset in {@link #classFileBuffer} of thestack_map_frame_value structure to be read, or the start offset of a full_frame structure (excluding its frame_type field).
 * @param compressed true to read a 'stack_map_frame' structure, false to read a 'full_frame'structure without its frame_type field.
 * @param expand if the stack map frame must be expanded. See {@link #EXPAND_FRAMES}.
 * @param context where the parsed stack map frame must be stored.
 * @return the end offset of the JVMS 'stack_map_frame' or 'full_frame' structure.
 */
private int readStackMapFrame(final int stackMapFrameOffset,final boolean compressed,final boolean expand,final Context context){
  int currentOffset=stackMapFrameOffset;
  final char[] charBuffer=context.charBuffer;
  final Label[] labels=context.currentMethodLabels;
  int frameType;
  if (compressed) {
    frameType=classFileBuffer[currentOffset++] & 0xFF;
  }
 else {
    frameType=Frame.FULL_FRAME;
    context.currentFrameOffset=-1;
  }
  int offsetDelta;
  context.currentFrameLocalCountDelta=0;
  if (frameType < Frame.SAME_LOCALS_1_STACK_ITEM_FRAME) {
    offsetDelta=frameType;
    context.currentFrameType=Opcodes.F_SAME;
    context.currentFrameStackCount=0;
  }
 else   if (frameType < Frame.RESERVED) {
    offsetDelta=frameType - Frame.SAME_LOCALS_1_STACK_ITEM_FRAME;
    currentOffset=readVerificationTypeInfo(currentOffset,context.currentFrameStackTypes,0,charBuffer,labels);
    context.currentFrameType=Opcodes.F_SAME1;
    context.currentFrameStackCount=1;
  }
 else   if (frameType >= Frame.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
    offsetDelta=readUnsignedShort(currentOffset);
    currentOffset+=2;
    if (frameType == Frame.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
      currentOffset=readVerificationTypeInfo(currentOffset,context.currentFrameStackTypes,0,charBuffer,labels);
      context.currentFrameType=Opcodes.F_SAME1;
      context.currentFrameStackCount=1;
    }
 else     if (frameType >= Frame.CHOP_FRAME && frameType < Frame.SAME_FRAME_EXTENDED) {
      context.currentFrameType=Opcodes.F_CHOP;
      context.currentFrameLocalCountDelta=Frame.SAME_FRAME_EXTENDED - frameType;
      context.currentFrameLocalCount-=context.currentFrameLocalCountDelta;
      context.currentFrameStackCount=0;
    }
 else     if (frameType == Frame.SAME_FRAME_EXTENDED) {
      context.currentFrameType=Opcodes.F_SAME;
      context.currentFrameStackCount=0;
    }
 else     if (frameType < Frame.FULL_FRAME) {
      int local=expand ? context.currentFrameLocalCount : 0;
      for (int k=frameType - Frame.SAME_FRAME_EXTENDED; k > 0; k--) {
        currentOffset=readVerificationTypeInfo(currentOffset,context.currentFrameLocalTypes,local++,charBuffer,labels);
      }
      context.currentFrameType=Opcodes.F_APPEND;
      context.currentFrameLocalCountDelta=frameType - Frame.SAME_FRAME_EXTENDED;
      context.currentFrameLocalCount+=context.currentFrameLocalCountDelta;
      context.currentFrameStackCount=0;
    }
 else {
      final int numberOfLocals=readUnsignedShort(currentOffset);
      currentOffset+=2;
      context.currentFrameType=Opcodes.F_FULL;
      context.currentFrameLocalCountDelta=numberOfLocals;
      context.currentFrameLocalCount=numberOfLocals;
      for (int local=0; local < numberOfLocals; ++local) {
        currentOffset=readVerificationTypeInfo(currentOffset,context.currentFrameLocalTypes,local,charBuffer,labels);
      }
      final int numberOfStackItems=readUnsignedShort(currentOffset);
      currentOffset+=2;
      context.currentFrameStackCount=numberOfStackItems;
      for (int stack=0; stack < numberOfStackItems; ++stack) {
        currentOffset=readVerificationTypeInfo(currentOffset,context.currentFrameStackTypes,stack,charBuffer,labels);
      }
    }
  }
 else {
    throw new IllegalArgumentException();
  }
  context.currentFrameOffset+=offsetDelta + 1;
  createLabel(context.currentFrameOffset,labels);
  return currentOffset;
}
