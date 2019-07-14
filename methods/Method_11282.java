public float getFontlength(Paint paint,String str){
  Rect rect=new Rect();
  paint.getTextBounds(str,0,str.length(),rect);
  return rect.width();
}
