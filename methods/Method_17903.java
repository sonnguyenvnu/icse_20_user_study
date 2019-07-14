private static boolean passesRootComponentBisect(String rootComponentName){
  final String start=ComponentsConfiguration.rootComponentBisectStart;
  final String end=ComponentsConfiguration.rootComponentBisectEnd;
  return rootComponentName.compareToIgnoreCase(start) >= 0 && rootComponentName.compareToIgnoreCase(end) <= 0;
}
