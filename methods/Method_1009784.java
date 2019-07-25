/** 
 * terminatinng, this should be called in GL context
 */
public void release(){
  if (hProgram >= 0)   GLES20.glDeleteProgram(hProgram);
  hProgram=-1;
}
