@Override protected void getGL(PGL pgl){
  PJOGL pjogl=(PJOGL)pgl;
  this.drawable=pjogl.drawable;
  this.context=pjogl.context;
  this.glContext=pjogl.glContext;
  setThread(pjogl.glThread);
  this.gl=pjogl.gl;
  this.gl2=pjogl.gl2;
  this.gl2x=pjogl.gl2x;
  this.gl3=pjogl.gl3;
  this.gl3es3=pjogl.gl3es3;
}
