public static void applyChatServiceMessageColor(int[] custom){
  if (chat_actionBackgroundPaint == null) {
    return;
  }
  Integer serviceColor;
  Integer servicePressedColor;
  serviceMessageColor=serviceMessageColorBackup;
  serviceSelectedMessageColor=serviceSelectedMessageColorBackup;
  if (custom != null && custom.length >= 2) {
    serviceColor=custom[0];
    servicePressedColor=custom[1];
    serviceMessageColor=custom[0];
    serviceSelectedMessageColor=custom[1];
  }
 else {
    serviceColor=currentColors.get(key_chat_serviceBackground);
    servicePressedColor=currentColors.get(key_chat_serviceBackgroundSelected);
  }
  Integer serviceColor2=serviceColor;
  Integer servicePressedColor2=servicePressedColor;
  if (serviceColor == null) {
    serviceColor=serviceMessageColor;
    serviceColor2=serviceMessage2Color;
  }
  if (servicePressedColor == null) {
    servicePressedColor=serviceSelectedMessageColor;
    servicePressedColor2=serviceSelectedMessage2Color;
  }
  if (currentColor != serviceColor) {
    chat_actionBackgroundPaint.setColor(serviceColor);
    colorFilter=new PorterDuffColorFilter(serviceColor,PorterDuff.Mode.MULTIPLY);
    colorFilter2=new PorterDuffColorFilter(serviceColor2,PorterDuff.Mode.MULTIPLY);
    currentColor=serviceColor;
    if (chat_cornerOuter[0] != null) {
      for (int a=0; a < 4; a++) {
        chat_cornerOuter[a].setColorFilter(colorFilter);
        chat_cornerInner[a].setColorFilter(colorFilter);
      }
    }
  }
  if (currentSelectedColor != servicePressedColor) {
    currentSelectedColor=servicePressedColor;
    colorPressedFilter=new PorterDuffColorFilter(servicePressedColor,PorterDuff.Mode.MULTIPLY);
    colorPressedFilter2=new PorterDuffColorFilter(servicePressedColor2,PorterDuff.Mode.MULTIPLY);
  }
}
