static private List<Font> getMonoFontList(){
  GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
  Font[] fonts=ge.getAllFonts();
  List<Font> outgoing=new ArrayList<>();
  FontRenderContext frc=new FontRenderContext(new AffineTransform(),Preferences.getBoolean("editor.antialias"),true);
  for (  Font font : fonts) {
    if (font.getStyle() == Font.PLAIN && font.canDisplay('i') && font.canDisplay('M') && font.canDisplay(' ') && font.canDisplay('.')) {
      double w=font.getStringBounds(" ",frc).getWidth();
      if (w == font.getStringBounds("i",frc).getWidth() && w == font.getStringBounds("M",frc).getWidth() && w == font.getStringBounds(".",frc).getWidth()) {
        outgoing.add(font);
      }
    }
  }
  return outgoing;
}
