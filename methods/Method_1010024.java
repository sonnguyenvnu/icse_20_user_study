/** 
 * Adds  {@link #content} to the given {@link #file}. Checks if file contains content and sends a notification.
 * @return previously provided file
 */
@Override protected PsiFile compute(){
  if (!content.isEmpty()) {
    final Document document=manager.getDocument(file);
    if (document != null) {
      file.acceptChildren(new IgnoreVisitor(){
        @Override public void visitEntry(        @NotNull IgnoreEntry entry){
          final VirtualFile moduleDir=Utils.getModuleRootForFile(file.getVirtualFile(),project);
          if (content.contains(entry.getText()) && moduleDir != null) {
            Notify.show(project,IgnoreBundle.message("action.appendFile.entryExists",entry.getText()),IgnoreBundle.message("action.appendFile.entryExists.in",Utils.getRelativePath(moduleDir,file.getVirtualFile())),NotificationType.WARNING);
            content.remove(entry.getText());
          }
        }
      }
);
      int offset=document.getTextLength();
      if (insertAtCursor) {
        Editor[] editors=EditorFactory.getInstance().getEditors(document);
        if (editors.length > 0) {
          VisualPosition position=editors[0].getSelectionModel().getSelectionStartPosition();
          if (position != null) {
            offset=document.getLineStartOffset(position.line);
          }
        }
      }
      for (      String entry : content) {
        if (ignoreDuplicates) {
          List<String> currentLines=ContainerUtil.filter(document.getText().split(Constants.NEWLINE),s -> !s.isEmpty() && !s.startsWith(Constants.HASH));
          List<String> entryLines=new ArrayList<>(Arrays.asList(entry.split(Constants.NEWLINE)));
          Iterator<String> iterator=entryLines.iterator();
          while (iterator.hasNext()) {
            String line=iterator.next().trim();
            if (line.isEmpty() || line.startsWith(Constants.HASH)) {
              continue;
            }
            if (currentLines.contains(line)) {
              iterator.remove();
            }
 else {
              currentLines.add(line);
            }
          }
          entry=StringUtil.join(entryLines,Constants.NEWLINE);
        }
        if (ignoreComments) {
          List<String> entryLines=new ArrayList<>(Arrays.asList(entry.split(Constants.NEWLINE)));
          Iterator<String> iterator=entryLines.iterator();
          while (iterator.hasNext()) {
            String line=iterator.next().trim();
            if (line.isEmpty() || line.startsWith(Constants.HASH)) {
              iterator.remove();
            }
          }
          entry=StringUtil.join(entryLines,Constants.NEWLINE);
        }
        entry=StringUtil.replace(entry,"\r","");
        if (!StringUtil.isEmpty(entry)) {
          entry+=Constants.NEWLINE;
        }
        if (!insertAtCursor && !document.getText().endsWith(Constants.NEWLINE) && !StringUtil.isEmpty(entry)) {
          entry=Constants.NEWLINE + entry;
        }
        document.insertString(offset,entry);
        offset+=entry.length();
      }
      manager.commitDocument(document);
    }
  }
  return file;
}
