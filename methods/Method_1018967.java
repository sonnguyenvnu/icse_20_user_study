/** 
 * Memory must be aligned correctly (on a 32-byte boundary) for the libvlc API functions, this is all taken care of by the  {@link ByteBufferFactory}.
 * @param bufferFormat
 * @return
 */
int allocate(BufferFormat bufferFormat){
  int planeCount=bufferFormat.getPlaneCount();
  int[] pitchValues=bufferFormat.getPitches();
  int[] lineValues=bufferFormat.getLines();
  nativeBuffers=new ByteBuffer[planeCount];
  pointers=new Pointer[planeCount];
  for (int i=0; i < planeCount; i++) {
    ByteBuffer buffer=ByteBufferFactory.allocateAlignedBuffer(pitchValues[i] * lineValues[i]);
    nativeBuffers[i]=buffer;
    pointers[i]=Pointer.createConstant(ByteBufferFactory.getAddress(buffer));
    if (lockBuffers) {
      if (!RuntimeUtil.isWindows()) {
        LibC.INSTANCE.mlock(pointers[i],new NativeLong(buffer.capacity()));
      }
 else {
        Kernel32.INSTANCE.VirtualLock(pointers[i],new size_t(buffer.capacity()));
      }
    }
  }
  return nativeBuffers.length;
}
