public void reset(PopupVPosition vAlign,PopupHPosition hAlign,double offsetX,double offsetY){
  scale.setPivotX(hAlign == PopupHPosition.RIGHT ? container.getWidth() : 0);
  scale.setPivotY(vAlign == PopupVPosition.BOTTOM ? container.getHeight() : 0);
  root.setTranslateX(hAlign == PopupHPosition.RIGHT ? -container.getWidth() + offsetX : offsetX);
  root.setTranslateY(vAlign == PopupVPosition.BOTTOM ? -container.getHeight() + offsetY : offsetY);
}
