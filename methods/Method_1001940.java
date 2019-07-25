private ArrayList<String> header(){
  final ArrayList<String> strings=new ArrayList<String>();
  strings.add("<b>PlantUML version " + Version.versionString() + "</b> (" + Version.compileTimeString() + ")");
  strings.add("(" + License.getCurrent() + " source distribution)");
  if (OptionFlags.ALLOW_INCLUDE) {
    strings.add("Loaded from " + Version.getJarPath());
  }
  strings.add(" ");
  return strings;
}
