@NativeType("CUresult") public static int cuGLRegisterBufferObject(@NativeType("GLuint") int buffer){
  long __functionAddress=Functions.GLRegisterBufferObject;
  return callI(buffer,__functionAddress);
}
