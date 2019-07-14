/** 
 * hide search view with a circular reveal animation
 */
public void hideSearchView(){
  final int END_RADIUS=16;
  int startRadius=Math.max(searchViewLayout.getWidth(),searchViewLayout.getHeight());
  Animator animator;
  if (SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
    int[] searchCoords=new int[2];
    View searchItem=appbar.getToolbar().findViewById(R.id.search);
    searchViewEditText.setText("");
    searchItem.getLocationOnScreen(searchCoords);
    animator=ViewAnimationUtils.createCircularReveal(searchViewLayout,searchCoords[0] + 32,searchCoords[1] - 16,startRadius,END_RADIUS);
  }
 else {
    animator=ObjectAnimator.ofFloat(searchViewLayout,"alpha",1f,0f);
  }
  mainActivity.hideSmokeScreen();
  animator.setInterpolator(new AccelerateDecelerateInterpolator());
  animator.setDuration(600);
  animator.start();
  animator.addListener(new Animator.AnimatorListener(){
    @Override public void onAnimationStart(    Animator animation){
    }
    @Override public void onAnimationEnd(    Animator animation){
      searchViewLayout.setVisibility(View.GONE);
      enabled=false;
      InputMethodManager inputMethodManager=(InputMethodManager)mainActivity.getSystemService(INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(searchViewEditText.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
    @Override public void onAnimationCancel(    Animator animation){
    }
    @Override public void onAnimationRepeat(    Animator animation){
    }
  }
);
}
