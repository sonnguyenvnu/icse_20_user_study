@Override public void getFloatv(int value,FloatBuffer data){
  if (-1 < value) {
    gl.glGetFloatv(value,data);
  }
 else {
    fillFloatBuffer(data,0,data.capacity() - 1,0);
  }
}
