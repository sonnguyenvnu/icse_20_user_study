private void updateButtonType(ButtonType type){
switch (type) {
case RAISED:
    JFXDepthManager.setDepth(getSkinnable(),2);
  clickedAnimation=new ButtonClickTransition(getSkinnable(),(DropShadow)getSkinnable().getEffect());
break;
default :
getSkinnable().setEffect(null);
break;
}
}
