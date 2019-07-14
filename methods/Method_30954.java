public static Drawable makeScrimDrawable(int baseColor,int numStops,int gravity){
  numStops=Math.max(numStops,2);
  PaintDrawable paintDrawable=new PaintDrawable();
  paintDrawable.setShape(new RectShape());
  final int[] stopColors=new int[numStops];
  int red=Color.red(baseColor);
  int green=Color.green(baseColor);
  int blue=Color.blue(baseColor);
  int alpha=Color.alpha(baseColor);
  for (int i=0; i < numStops; ++i) {
    float x=i * 1f / (numStops - 1);
    float opacity=MathUtils.constrain((float)Math.pow(x,3),0,1);
    stopColors[i]=Color.argb((int)(alpha * opacity),red,green,blue);
  }
  final float x0, x1, y0, y1;
switch (gravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
case Gravity.LEFT:
    x0=1;
  x1=0;
break;
case Gravity.RIGHT:
x0=0;
x1=1;
break;
default :
x0=0;
x1=0;
break;
}
switch (gravity & Gravity.VERTICAL_GRAVITY_MASK) {
case Gravity.TOP:
y0=1;
y1=0;
break;
case Gravity.BOTTOM:
y0=0;
y1=1;
break;
default :
y0=0;
y1=0;
break;
}
paintDrawable.setShaderFactory(new ShapeDrawable.ShaderFactory(){
@Override public Shader resize(int width,int height){
return new LinearGradient(width * x0,height * y0,width * x1,height * y1,stopColors,null,Shader.TileMode.CLAMP);
}
}
);
paintDrawable.setAlpha(Math.round(0.4f * 255));
return paintDrawable;
}
