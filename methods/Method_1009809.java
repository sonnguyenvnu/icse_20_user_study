private void init(Context context){
  Bitmap bitmap=((BitmapDrawable)getResources().getDrawable(R.drawable.xyjy2)).getBitmap();
  clampShader=new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
  mirrorShader=new BitmapShader(bitmap,Shader.TileMode.MIRROR,Shader.TileMode.MIRROR);
  repeatShader=new BitmapShader(bitmap,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
  paint=new Paint();
  paint.setShader(mirrorShader);
  paint.setAntiAlias(true);
  width=bitmap.getWidth();
  height=bitmap.getHeight();
  float scale=Math.max(width,height) / Math.min(width,height);
  matrix=new Matrix();
  matrix.setScale(scale,scale);
  clampShader.setLocalMatrix(matrix);
  mirrorShader.setLocalMatrix(matrix);
  repeatShader.setLocalMatrix(matrix);
}
