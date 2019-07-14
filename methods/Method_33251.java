@Override protected Node getPopupContent(){
  if (popupContent == null) {
    popupContent=new JFXColorPalette((ColorPicker)getSkinnable());
    popupContent.setPopupControl(getPopup());
  }
  return popupContent;
}
