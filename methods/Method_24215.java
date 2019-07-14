public void getGL(GLAutoDrawable glDrawable){
  context=glDrawable.getContext();
  glContext=context.hashCode();
  setThread(Thread.currentThread());
  gl=context.getGL();
  gl2=gl.getGL2ES2();
  try {
    gl2x=gl.getGL2();
  }
 catch (  com.jogamp.opengl.GLException e) {
    gl2x=null;
  }
  try {
    gl3=gl.getGL2GL3();
  }
 catch (  com.jogamp.opengl.GLException e) {
    gl3=null;
  }
  try {
    gl3es3=gl.getGL3ES3();
  }
 catch (  com.jogamp.opengl.GLException e) {
    gl3es3=null;
  }
}
