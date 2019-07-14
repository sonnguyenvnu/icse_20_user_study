private void ensureSubtitleAnimatorTarget(){
  if (mSubtitleAnimator.getTarget() != null) {
    return;
  }
  TextView subtitleTextView;
  try {
    Field subtitleTextViewField=Toolbar.class.getDeclaredField("mSubtitleTextView");
    subtitleTextViewField.setAccessible(true);
    subtitleTextView=(TextView)subtitleTextViewField.get(this);
  }
 catch (  NoSuchFieldException e) {
    e.printStackTrace();
    return;
  }
catch (  IllegalAccessException e) {
    e.printStackTrace();
    return;
  }
  if (subtitleTextView == null) {
    return;
  }
  ViewUtils.setWidth(subtitleTextView,LayoutParams.MATCH_PARENT);
  mSubtitleAnimator.setTarget(subtitleTextView);
}
