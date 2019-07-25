/** 
 * ???????????
 */
public void scroll(int position,float offset){
  if (getChildAt(position + 1) != null) {
    TextView end=(TextView)getChildAt(position + 1);
    Rect bound=new Rect();
    end.getPaint().getTextBounds(end.getText().toString(),0,end.getText().length(),bound);
    int endLength=bound.width() + 20;
    int endTranslationX=(end.getMeasuredWidth() - endLength) / 2 + end.getLeft();
    TextView start=(TextView)getChildAt(position);
    start.getPaint().getTextBounds(start.getText().toString(),0,start.getText().length(),bound);
    int startLength=bound.width() + 20;
    int startTranslationX=(start.getMeasuredWidth() - startLength) / 2 + start.getLeft();
    mTranslationX=startTranslationX + (endTranslationX - startTranslationX) * offset;
    float value=(endLength - startLength) * offset;
    mPath.reset();
    mPath.moveTo(0,0);
    mPath.lineTo(value + startLength,0);
    mPath.lineTo(value + startLength,lineHeight);
    mPath.lineTo(0,value + startLength);
    mPath.close();
  }
  invalidate();
}
