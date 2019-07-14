@NativeType("CUresult") public static int cuGLUnregisterBufferObject(@NativeType("GLuint") int buffer){
  long __functionAddress=Functions.GLUnregisterBufferObject;
  return callI(buffer,__functionAddress);
}
