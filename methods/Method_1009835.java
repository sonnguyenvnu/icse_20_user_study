@Override public void finish(){
  super.finish();
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    overridePendingTransition(R.anim.anim_slide_nothing,R.anim.anim_slide_out_bottom);
  }
 else {
  }
}
