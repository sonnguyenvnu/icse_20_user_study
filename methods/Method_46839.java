/** 
 * show search view with a circular reveal animation
 */
void revealSearchView(){
  int startRadius=4;
  int endRadius=Math.max(searchViewLayout.getWidth(),searchViewLayout.getHeight());
  DisplayMetrics metrics=new DisplayMetrics();
  getWindowManager().getDefaultDisplay().getMetrics(metrics);
  int cx=metrics.widthPixels - 160;
  int cy=toolbar.getBottom();
  Animator animator;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)   animator=ViewAnimationUtils.createCircularReveal(searchViewLayout,cx,cy,startRadius,endRadius);
 else   animator=ObjectAnimator.ofFloat(searchViewLayout,"alpha",0f,1f);
  animator.setInterpolator(new AccelerateDecelerateInterpolator());
  animator.setDuration(600);
  searchViewLayout.setVisibility(View.VISIBLE);
  searchEditText.setText("");
  animator.start();
  animator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      searchEditText.requestFocus();
      InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(searchEditText,InputMethodManager.SHOW_IMPLICIT);
    }
  }
);
}
