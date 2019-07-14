private Rect calculateFocusArea(float x,float y){
  int padding=getFocusAreaSize() / 2;
  int centerX=(int)(x * 2000);
  int centerY=(int)(y * 2000);
  int left=centerX - padding;
  int top=centerY - padding;
  int right=centerX + padding;
  int bottom=centerY + padding;
  if (left < 0)   left=0;
  if (right > 2000)   right=2000;
  if (top < 0)   top=0;
  if (bottom > 2000)   bottom=2000;
  return new Rect(left - 1000,top - 1000,right - 1000,bottom - 1000);
}
