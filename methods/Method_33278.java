private void updateColorFromUserInput(String colorWebString){
  if (!systemChange) {
    userChange=true;
    try {
      curvedColorPicker.setColor(Color.valueOf(colorWebString));
    }
 catch (    IllegalArgumentException ignored) {
    }
    userChange=false;
  }
}
