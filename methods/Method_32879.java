public void updateWith(Properties props){
  reset();
  for (  Map.Entry<Object,Object> entries : props.entrySet()) {
    String key=(String)entries.getKey();
    String value=(String)entries.getValue();
    int colorIndex;
    if (key.equals("foreground")) {
      colorIndex=TextStyle.COLOR_INDEX_FOREGROUND;
    }
 else     if (key.equals("background")) {
      colorIndex=TextStyle.COLOR_INDEX_BACKGROUND;
    }
 else     if (key.equals("cursor")) {
      colorIndex=TextStyle.COLOR_INDEX_CURSOR;
    }
 else     if (key.startsWith("color")) {
      try {
        colorIndex=Integer.parseInt(key.substring(5));
      }
 catch (      NumberFormatException e) {
        throw new IllegalArgumentException("Invalid property: '" + key + "'");
      }
    }
 else {
      throw new IllegalArgumentException("Invalid property: '" + key + "'");
    }
    int colorValue=TerminalColors.parse(value);
    if (colorValue == 0)     throw new IllegalArgumentException("Property '" + key + "' has invalid color: '" + value + "'");
    mDefaultColors[colorIndex]=colorValue;
  }
}
