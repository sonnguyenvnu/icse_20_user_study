@Override public void queueInput(ByteBuffer inputBuffer){
  int position=inputBuffer.position();
  int limit=inputBuffer.limit();
  int size=limit - position;
  int resampledSize;
switch (encoding) {
case C.ENCODING_PCM_8BIT:
    resampledSize=size * 2;
  break;
case C.ENCODING_PCM_24BIT:
resampledSize=(size / 3) * 2;
break;
case C.ENCODING_PCM_32BIT:
resampledSize=size / 2;
break;
case C.ENCODING_PCM_16BIT:
case C.ENCODING_PCM_FLOAT:
case C.ENCODING_PCM_A_LAW:
case C.ENCODING_PCM_MU_LAW:
case C.ENCODING_INVALID:
case Format.NO_VALUE:
default :
throw new IllegalStateException();
}
if (buffer.capacity() < resampledSize) {
buffer=ByteBuffer.allocateDirect(resampledSize).order(ByteOrder.nativeOrder());
}
 else {
buffer.clear();
}
switch (encoding) {
case C.ENCODING_PCM_8BIT:
for (int i=position; i < limit; i++) {
buffer.put((byte)0);
buffer.put((byte)((inputBuffer.get(i) & 0xFF) - 128));
}
break;
case C.ENCODING_PCM_24BIT:
for (int i=position; i < limit; i+=3) {
buffer.put(inputBuffer.get(i + 1));
buffer.put(inputBuffer.get(i + 2));
}
break;
case C.ENCODING_PCM_32BIT:
for (int i=position; i < limit; i+=4) {
buffer.put(inputBuffer.get(i + 2));
buffer.put(inputBuffer.get(i + 3));
}
break;
case C.ENCODING_PCM_16BIT:
case C.ENCODING_PCM_FLOAT:
case C.ENCODING_PCM_A_LAW:
case C.ENCODING_PCM_MU_LAW:
case C.ENCODING_INVALID:
case Format.NO_VALUE:
default :
throw new IllegalStateException();
}
inputBuffer.position(inputBuffer.limit());
buffer.flip();
outputBuffer=buffer;
}
