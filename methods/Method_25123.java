@Override public void writeFile(SourceFile update) throws IOException {
  Path sourceFilePath=rootPath.resolve(update.getPath());
  String oldSource=new String(Files.readAllBytes(sourceFilePath),UTF_8);
  String newSource=update.getSourceText();
  if (!oldSource.equals(newSource)) {
    List<String> originalLines=LINE_SPLITTER.splitToList(oldSource);
    Patch<String> diff=DiffUtils.diff(originalLines,LINE_SPLITTER.splitToList(newSource));
    String relativePath=relativize(sourceFilePath);
    List<String> unifiedDiff=DiffUtils.generateUnifiedDiff(relativePath,relativePath,originalLines,diff,2);
    String diffString=Joiner.on("\n").join(unifiedDiff) + "\n";
    diffByFile.put(sourceFilePath.toUri(),diffString);
  }
}
