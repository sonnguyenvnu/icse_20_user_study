private static ModeInfo modeInfoFor(final File sketch){
  final File sketchFolder=sketch.getParentFile();
  final File sketchProps=new File(sketchFolder,"sketch.properties");
  if (!sketchProps.exists()) {
    return null;
  }
  try {
    final Settings settings=new Settings(sketchProps);
    final String title=settings.get("mode");
    final String id=settings.get("mode.id");
    if (title == null || id == null) {
      return null;
    }
    return new ModeInfo(id,title);
  }
 catch (  IOException e) {
    System.err.println("While trying to read " + sketchProps + ": " + e.getMessage());
  }
  return null;
}
