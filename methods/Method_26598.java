@VisibleForTesting static String fromSecondLevel(String qualifiedTemplateClass){
  List<String> path=Splitter.on('.').splitToList(qualifiedTemplateClass);
  for (int topLevel=0; topLevel < path.size() - 1; topLevel++) {
    if (Ascii.isUpperCase(path.get(topLevel).charAt(0))) {
      return Joiner.on('_').join(path.subList(topLevel + 1,path.size()));
    }
  }
  return Iterables.getLast(path);
}
