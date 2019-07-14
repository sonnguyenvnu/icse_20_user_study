private void moveTipToCorrectPosition(TextView tipView,Point p){
  RxCoordinates tipViewRxCoordinates=new RxCoordinates(tipView);
  int translationX=p.x - tipViewRxCoordinates.left;
  int translationY=p.y - tipViewRxCoordinates.top;
  if (!RxPopupViewTool.isRtl())   tipView.setTranslationX(translationX);
 else   tipView.setTranslationX(-translationX);
  tipView.setTranslationY(translationY);
}
