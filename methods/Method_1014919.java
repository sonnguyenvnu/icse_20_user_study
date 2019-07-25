protected static void setup(GL2 gl2,int width,int height){
  gl2.glMatrixMode(GL2.GL_PROJECTION);
  gl2.glLoadIdentity();
  GLU glu=new GLU();
  glu.gluOrtho2D(0.0f,width,0.0f,height);
  gl2.glMatrixMode(GL2.GL_MODELVIEW);
  gl2.glLoadIdentity();
  gl2.glViewport(0,0,width,height);
}
