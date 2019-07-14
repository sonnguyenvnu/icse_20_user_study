private void removePressedLink(){
  if (pressedLink == null && pressedLinkOwnerView == null) {
    return;
  }
  View parentView=pressedLinkOwnerView;
  pressedLink=null;
  pressedLinkOwnerLayout=null;
  pressedLinkOwnerView=null;
  if (parentView != null) {
    parentView.invalidate();
  }
}
