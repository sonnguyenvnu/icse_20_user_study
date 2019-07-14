@Override public void getBooleanv(int value,IntBuffer data){
  if (-1 < value) {
    if (byteBuffer.capacity() < data.capacity()) {
      byteBuffer=allocateDirectByteBuffer(data.capacity());
    }
    gl.glGetBooleanv(value,byteBuffer);
    for (int i=0; i < data.capacity(); i++) {
      data.put(i,byteBuffer.get(i));
    }
  }
 else {
    fillIntBuffer(data,0,data.capacity() - 1,0);
  }
}
