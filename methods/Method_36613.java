/** 
 * ????????????
 * @param message      ???
 * @param inAnimResId  ?????resID
 * @param outAnimResID ?????resID
 */
public void startWithText(final String message,final @AnimRes int inAnimResId,final @AnimRes int outAnimResID){
  if (TextUtils.isEmpty(message))   return;
  getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
    @Override public void onGlobalLayout(){
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
      }
 else {
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
      startWithFixedWidth(message,inAnimResId,outAnimResID);
    }
  }
);
}
