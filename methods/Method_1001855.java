private List<String> init(){
  final List<String> strings=anErrorHasOccured(null,text);
  strings.add("For some reason, dot/GraphViz has crashed.");
  strings.add("This has been generated with PlantUML (" + Version.versionString() + ").");
  checkOldVersionWarning(strings);
  strings.add(" ");
  addProperties(strings);
  strings.add(" ");
  try {
    final String dotVersion=GraphvizUtils.dotVersion();
    strings.add("Default dot version: " + dotVersion);
  }
 catch (  Throwable e) {
    strings.add("Cannot determine dot version: " + e.toString());
  }
  pleaseGoTo(strings);
  youShouldSendThisDiagram(strings);
  if (flashCode != null) {
    addDecodeHint(strings);
  }
  return strings;
}
