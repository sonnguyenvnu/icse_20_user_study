public void updateAppearance(){
  setForeground(defaults.fgcolor);
  setBackground(defaults.bgcolor);
  Toolkit.getMonoFontName();
  String fontFamily=Preferences.get("editor.font.family");
  final int fontSize=Toolkit.zoom(Preferences.getInteger("editor.font.size"));
  plainFont=new Font(fontFamily,Font.PLAIN,fontSize);
  if (!fontFamily.equals(plainFont.getFamily())) {
    System.err.println(fontFamily + " not available, resetting to monospaced");
    fontFamily="Monospaced";
    Preferences.set("editor.font.family",fontFamily);
    plainFont=new Font(fontFamily,Font.PLAIN,fontSize);
  }
  boldFont=new Font(fontFamily,Font.BOLD,fontSize);
  antialias=Preferences.getBoolean("editor.smooth");
  fm=super.getFontMetrics(plainFont);
  tabSize=fm.charWidth(' ') * Preferences.getInteger("editor.tabs.size");
  textArea.recalculateVisibleLines();
}
