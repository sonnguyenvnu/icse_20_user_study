public void init(GLAutoDrawable glDrawable){
  capabilities=glDrawable.getChosenGLCapabilities();
  if (!hasFBOs()) {
    throw new RuntimeException(MISSING_FBO_ERROR);
  }
  if (!hasShaders()) {
    throw new RuntimeException(MISSING_GLSL_ERROR);
  }
}
