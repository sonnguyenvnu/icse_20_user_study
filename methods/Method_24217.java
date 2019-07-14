@Override protected boolean hasShaders(){
  if (context.hasGLSL())   return true;
 else   return super.hasShaders();
}
