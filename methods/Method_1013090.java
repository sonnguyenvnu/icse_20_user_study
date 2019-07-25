@NotNull @Override public Map<String,Set<String>> map(@NotNull FileContent inputData){
  final Map<String,Set<String>> indexMap=new HashMap<>();
  if (inputData.getFileType().isBinary()) {
    return indexMap;
  }
  final PsiFile file=inputData.getPsiFile();
  if (shouldIndex(file)) {
    final VirtualFile specDirectory=inputData.getFile().getParent();
    final Set<String> referencedFiles=getReferencedFiles(file,specDirectory);
    indexMap.put(MAIN_SPEC_FILES,ImmutableSet.of(file.getName() + DELIMITER + mainSpecFileType.toString()));
    indexMap.put(PARTIAL_SPEC_FILES,referencedFiles);
  }
  return indexMap;
}
