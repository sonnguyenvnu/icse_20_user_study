/** 
 * ??
 */
public void show(){
  if (mCookieView != null && getActivity() != null) {
    final ViewGroup decorView=(ViewGroup)getActivity().getWindow().getDecorView();
    final ViewGroup content=decorView.findViewById(android.R.id.content);
    if (mCookieView.getParent() == null) {
      if (mCookieView.getLayoutGravity() == Gravity.BOTTOM) {
        content.addView(mCookieView);
      }
 else {
        decorView.addView(mCookieView);
      }
    }
  }
}
