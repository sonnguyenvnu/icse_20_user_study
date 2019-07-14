static private List<String> buildSketchLibraryClassPath(JavaMode mode,List<ImportStatement> programImports){
  StringBuilder classPath=new StringBuilder();
  programImports.stream().map(ImportStatement::getPackageName).filter(pckg -> !ignorableImport(pckg)).map(pckg -> {
    try {
      return mode.getLibrary(pckg);
    }
 catch (    SketchException e) {
      return null;
    }
  }
).filter(lib -> lib != null).map(Library::getClassPath).forEach(cp -> classPath.append(File.pathSeparator).append(cp));
  return sanitizeClassPath(classPath.toString());
}
