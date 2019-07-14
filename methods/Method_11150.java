public boolean findAndDismiss(final View anchorView){
  View view=find(anchorView.getId());
  return view != null && dismiss(view,false);
}
