public void statusToolTip(JComponent comp,String message,boolean error){
  if (font == null) {
    font=Toolkit.getSansFont(Toolkit.zoom(9),Font.PLAIN);
    textColor=mode.getColor("errors.selection.fgcolor");
    bgColorWarning=mode.getColor("errors.selection.warning.bgcolor");
    bgColorError=mode.getColor("errors.selection.error.bgcolor");
  }
  Color bgColor=error ? bgColorError : bgColorWarning;
  int m=Toolkit.zoom(3);
  String css=String.format("margin: %d %d %d %d; ",-m,-m,-m,-m) + String.format("padding: %d %d %d %d; ",m,m,m,m) + "background: #" + PApplet.hex(bgColor.getRGB(),8).substring(2) + ";" + "font-family: " + font.getFontName() + ", sans-serif;" + "font-size: " + font.getSize() + "px;";
  String content="<html> <div style='" + css + "'>" + message + "</div> </html>";
  comp.setToolTipText(content);
}
