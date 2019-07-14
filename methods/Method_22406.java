/** 
 * Create or modify a sketch.proprties file to specify the given Mode.
 */
private void saveModeSettings(final File sketchProps,final Mode mode){
  try {
    final Settings settings=new Settings(sketchProps);
    settings.set("mode",mode.getTitle());
    settings.set("mode.id",mode.getIdentifier());
    settings.save();
  }
 catch (  IOException e) {
    System.err.println("While creating " + sketchProps + ": " + e.getMessage());
  }
}
