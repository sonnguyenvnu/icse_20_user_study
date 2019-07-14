/** 
 * * Transitions                                                              *
 */
private Transition getShowAnimation(DialogTransition transitionType){
  Transition animation=null;
  if (contentHolder != null) {
switch (transitionType) {
case LEFT:
      contentHolder.setScaleX(1);
    contentHolder.setScaleY(1);
  contentHolder.setTranslateX(-offsetX);
animation=new LeftTransition();
break;
case RIGHT:
contentHolder.setScaleX(1);
contentHolder.setScaleY(1);
contentHolder.setTranslateX(offsetX);
animation=new RightTransition();
break;
case TOP:
contentHolder.setScaleX(1);
contentHolder.setScaleY(1);
contentHolder.setTranslateY(-offsetY);
animation=new TopTransition();
break;
case BOTTOM:
contentHolder.setScaleX(1);
contentHolder.setScaleY(1);
contentHolder.setTranslateY(offsetY);
animation=new BottomTransition();
break;
case CENTER:
contentHolder.setScaleX(0);
contentHolder.setScaleY(0);
animation=new CenterTransition();
break;
default :
animation=null;
contentHolder.setScaleX(1);
contentHolder.setScaleY(1);
contentHolder.setTranslateX(0);
contentHolder.setTranslateY(0);
break;
}
}
if (animation != null) {
animation.setOnFinished(finish -> Event.fireEvent(JFXDialog.this,new JFXDialogEvent(JFXDialogEvent.OPENED)));
}
return animation;
}
