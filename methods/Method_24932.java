public void selectorChanged(int hue,int saturation,int brightness){
  if (isBW) {
    handles.get(0).setValue((float)hue / 255 * colorMode.v1Max);
  }
 else {
    if (handles.size() == 1 || handles.size() == 2) {
      int prevVal=handles.get(0).newValue.intValue();
      int prevAlpha=(prevVal >> 24) & 0xff;
      Color c=Color.getHSBColor((float)hue / 255,(float)saturation / 255,(float)brightness / 255);
      int newVal=(prevAlpha << 24) | (c.getRed() << 16) | (c.getGreen() << 8) | (c.getBlue());
      handles.get(0).setValue(newVal);
    }
 else     if (handles.size() == 3 || handles.size() == 4) {
      if (colorMode.modeType == ColorMode.HSB) {
        float v1=(float)hue / 255 * colorMode.v1Max;
        float v2=(float)saturation / 255 * colorMode.v2Max;
        float v3=(float)brightness / 255 * colorMode.v3Max;
        handles.get(0).setValue(v1);
        handles.get(1).setValue(v2);
        handles.get(2).setValue(v3);
      }
 else {
        Color c=Color.getHSBColor((float)hue / 255,(float)saturation / 255,(float)brightness / 255);
        handles.get(0).setValue((float)c.getRed() / 255 * colorMode.v1Max);
        handles.get(1).setValue((float)c.getGreen() / 255 * colorMode.v2Max);
        handles.get(2).setValue((float)c.getBlue() / 255 * colorMode.v3Max);
      }
    }
  }
  color=getCurrentColor();
  painter.updateCodeText();
  painter.repaint();
}
