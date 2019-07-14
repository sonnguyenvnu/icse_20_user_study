@Override public void onSingleTapUp(MotionEvent e){
  InputMethodManager mgr=(InputMethodManager)mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
  mgr.showSoftInput(mActivity.mTerminalView,InputMethodManager.SHOW_IMPLICIT);
}
