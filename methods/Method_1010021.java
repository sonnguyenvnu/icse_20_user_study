/** 
 * Helper for inspection message generating.
 * @param coveringEntry entry that covers message related
 * @param virtualFile   current working file
 * @param onTheFly      true if called during on the fly editor highlighting. Called from Inspect Code actionotherwise
 * @return generated message {@link String}
 */
@NotNull private static String message(@NotNull IgnoreEntry coveringEntry,@NotNull VirtualFile virtualFile,boolean onTheFly){
  Document document=FileDocumentManager.getInstance().getDocument(virtualFile);
  if (onTheFly || document == null) {
    return IgnoreBundle.message("codeInspection.coverEntry.message","\'" + coveringEntry.getText() + "\'");
  }
  int startOffset=coveringEntry.getTextRange().getStartOffset();
  return IgnoreBundle.message("codeInspection.coverEntry.message","<a href=\"" + virtualFile.getUrl() + Constants.HASH + startOffset + "\">" + coveringEntry.getText() + "</a>");
}
