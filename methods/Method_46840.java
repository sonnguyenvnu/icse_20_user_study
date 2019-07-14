/** 
 * hide search view with a circular reveal animation
 */
void hideSearchView(){
  int endRadius=4;
  int startRadius=Math.max(searchViewLayout.getWidth(),searchViewLayout.getHeight());
  DisplayMetrics metrics=new DisplayMetrics();
  getWindowManager().getDefaultDisplay().getMetrics(metrics);
  int cx=metrics.widthPixels - 160;
  int cy=toolbar.getBottom();
  Animator animator;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    animator=ViewAnimationUtils.createCircularReveal(searchViewLayout,cx,cy,startRadius,endRadius);
  }
 else {
    animator=ObjectAnimator.ofFloat(searchViewLayout,"alpha",0f,1f);
  }
  animator.setInterpolator(new AccelerateDecelerateInterpolator());
  animator.setDuration(600);
  animator.start();
  animator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      searchViewLayout.setVisibility(View.GONE);
      InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(searchEditText.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
  }
);
}
