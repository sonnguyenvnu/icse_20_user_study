private LinearGradient createShader(int startColor,int endColor,ShaderMode shaderMode,float x0,float y0,float x1,float y1){
  if (startColor != 0 && endColor != 0) {
    int temp=0;
switch (shaderMode) {
case TOP_TO_BOTTOM:
      x1=x0;
    break;
case BOTTOM_TO_TOP:
  x1=x0;
temp=startColor;
startColor=endColor;
endColor=temp;
break;
case LEFT_TO_RIGHT:
y1=y0;
break;
case RIGHT_TO_LEFT:
y1=y0;
temp=startColor;
startColor=endColor;
endColor=temp;
break;
}
return new LinearGradient(x0,y0,x1,y1,startColor,endColor,Shader.TileMode.CLAMP);
}
 else {
return null;
}
}
