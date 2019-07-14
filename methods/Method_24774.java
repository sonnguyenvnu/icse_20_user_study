protected static boolean ignorableSuggestionImport(PreprocessedSketch ps,String impName){
  String impNameLc=impName.toLowerCase();
  List<ImportStatement> programImports=ps.programImports;
  List<ImportStatement> codeFolderImports=ps.codeFolderImports;
  boolean isImported=Stream.concat(programImports.stream(),codeFolderImports.stream()).anyMatch(impS -> {
    String packageNameLc=impS.getPackageName().toLowerCase();
    return impNameLc.startsWith(packageNameLc);
  }
);
  if (isImported)   return false;
  final String include="include";
  final String exclude="exclude";
  if (impName.startsWith("processing")) {
    if (JavaMode.suggestionsMap.containsKey(include) && JavaMode.suggestionsMap.get(include).contains(impName)) {
      return false;
    }
 else     if (JavaMode.suggestionsMap.containsKey(exclude) && JavaMode.suggestionsMap.get(exclude).contains(impName)) {
      return true;
    }
  }
 else   if (impName.startsWith("java")) {
    if (JavaMode.suggestionsMap.containsKey(include) && JavaMode.suggestionsMap.get(include).contains(impName)) {
      return false;
    }
  }
  return true;
}
