private static List<String> getAvaialbeFonts(){
  final List<String> ret=new ArrayList<>();
  final GraphicsEnvironment e=GraphicsEnvironment.getLocalGraphicsEnvironment();
  final Font[] fonts=e.getAllFonts();
  for (  final Font f : fonts) {
    if (Strings.contains(f.getFontName(),new String[]{"Verdana","DejaVu Sans Mono","Tahoma"})) {
      ret.add(f.getFontName());
    }
  }
  final String defaultFontName=new JLabel().getFont().getFontName();
  ret.add(defaultFontName);
  return ret;
}
