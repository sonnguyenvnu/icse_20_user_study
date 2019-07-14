/** 
 * calculate the amount of movement need to be taken inorder to align tip on X axis according to "align" parameter
 * @return int
 */
private static int getXOffset(View tipView,RxPopupView rxPopupView){
  int offset;
switch (rxPopupView.getAlign()) {
case RxPopupView.ALIGN_CENTER:
    offset=((rxPopupView.getAnchorView().getWidth() - tipView.getMeasuredWidth()) / 2);
  break;
case RxPopupView.ALIGN_LEFT:
offset=0;
break;
case RxPopupView.ALIGN_RIGHT:
offset=rxPopupView.getAnchorView().getWidth() - tipView.getMeasuredWidth();
break;
default :
offset=0;
break;
}
return offset;
}
