private static void generateTextureIds(int[] textureIdHolder){
  GLES20.glGenTextures(1,textureIdHolder,0);
  GlUtil.checkGlError();
}
