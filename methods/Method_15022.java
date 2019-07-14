@Override public MomentItem getData(){
  return llMomentViewContainer.getVisibility() == View.VISIBLE ? data : null;
}
