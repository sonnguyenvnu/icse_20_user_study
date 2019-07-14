/** 
 * show search view with a circular reveal animation
 */
public void revealSearchView(){
  final int START_RADIUS=16;
  int endRadius=Math.max(appbar.getToolbar().getWidth(),appbar.getToolbar().getHeight());
  Animator animator;
  if (SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
    int[] searchCoords=new int[2];
    View searchItem=appbar.getToolbar().findViewById(R.id.search);
    searchViewEditText.setText("");
    searchItem.getLocationOnScreen(searchCoords);
    animator=ViewAnimationUtils.createCircularReveal(searchViewLayout,searchCoords[0] + 32,searchCoords[1] - 16,START_RADIUS,endRadius);
  }
 else {
    animator=ObjectAnimator.ofFloat(searchViewLayout,"alpha",0f,1f);
  }
  mainActivity.showSmokeScreen();
  animator.setInterpolator(new AccelerateDecelerateInterpolator());
  animator.setDuration(600);
  searchViewLayout.setVisibility(View.VISIBLE);
  animator.start();
  animator.addListener(new Animator.AnimatorListener(){
    @Override public void onAnimationStart(    Animator animation){
    }
    @Override public void onAnimationEnd(    Animator animation){
      searchViewEditText.requestFocus();
      InputMethodManager imm=(InputMethodManager)mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(searchViewEditText,InputMethodManager.SHOW_IMPLICIT);
      enabled=true;
    }
    @Override public void onAnimationCancel(    Animator animation){
    }
    @Override public void onAnimationRepeat(    Animator animation){
    }
  }
);
}
