@NotNull private static Optional<Integer> offset(@NotNull String path,@NotNull Matcher matcher){
  VirtualFile virtualFile=LocalFileSystem.getInstance().findFileByPath(path);
  Optional<Integer> offset=Optional.empty();
  if (virtualFile != null) {
    Document document=FileDocumentManager.getInstance().getDocument(virtualFile);
    if (document != null) {
      int lineNumber=Integer.parseInt(matcher.group("lineNumber"));
      int start=document.getLineStartOffset(lineNumber - 1);
      @Nullable String formattedColumnNumber=matcher.group("columnNumber");
      if (formattedColumnNumber != null) {
        int column=Integer.parseInt(formattedColumnNumber) - 1;
        offset=Optional.of(start + column);
      }
 else {
        offset=Optional.of(start);
      }
    }
  }
  return offset;
}
