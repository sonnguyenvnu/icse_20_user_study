private static BetterLinkMovementExtended linkify(int linkifyMask,TextView textView){
  BetterLinkMovementExtended movementMethod=new BetterLinkMovementExtended(textView.getContext());
  addLinks(linkifyMask,movementMethod,textView);
  return movementMethod;
}
