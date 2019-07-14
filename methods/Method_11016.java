public static Shader createAlphaPatternShader(int size){
  size/=2;
  size=Math.max(8,size * 2);
  return new BitmapShader(createAlphaBackgroundPattern(size),Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
}
