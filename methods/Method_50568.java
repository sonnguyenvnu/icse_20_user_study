private void drawShaderText(Canvas canvas){
  Shader tempShader=getPaint().getShader();
  if (getLayout() != null && getLayout().getLineCount() > 0) {
    float x0=getLayout().getLineLeft(0);
    int y0=getLayout().getLineTop(0);
    float x1=x0 + getLayout().getLineWidth(0);
    float y1=y0 + getLayout().getHeight();
    if (getLayout().getLineCount() > 1) {
      for (int i=1; i < getLayout().getLineCount(); i++) {
        if (x0 > getLayout().getLineLeft(i)) {
          x0=getLayout().getLineLeft(i);
        }
        if (x1 < x0 + getLayout().getLineWidth(i)) {
          x1=x0 + getLayout().getLineWidth(i);
        }
      }
    }
    if (textShader == null) {
      textShader=createShader(textShaderStartColor,textShaderEndColor,textShaderMode,x0,y0,x1,y1);
    }
    getPaint().setShader(textShader);
    sdkOnDraw(canvas);
  }
  getPaint().setShader(tempShader);
}
