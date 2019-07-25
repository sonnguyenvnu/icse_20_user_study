private void parse(){
  try {
    try {
      readNullTerminatedString();
      mIdSize=mInput.readInt();
      mSnapshot.setIdSize(mIdSize);
      mInput.readLong();
      while (mInput.hasRemaining()) {
        int tag=readUnsignedByte();
        mInput.readInt();
        long length=readUnsignedInt();
switch (tag) {
case STRING_IN_UTF8:
          loadString((int)length - mIdSize);
        break;
case LOAD_CLASS:
      loadClass();
    break;
case STACK_FRAME:
  loadStackFrame();
break;
case STACK_TRACE:
loadStackTrace();
break;
case HEAP_DUMP:
loadHeapDump(length);
mSnapshot.setToDefaultHeap();
break;
case HEAP_DUMP_SEGMENT:
loadHeapDump(length);
mSnapshot.setToDefaultHeap();
break;
default :
skipFully(length);
}
}
}
 catch (EOFException eof) {
}
mSnapshot.resolveClasses();
mSnapshot.identifySoftReferences();
}
 catch (Exception e) {
throw new RuntimeException("Could not parse heap dump",e);
}
mClassNamesById.clear();
mClassNamesBySerial.clear();
mStrings.clear();
}
