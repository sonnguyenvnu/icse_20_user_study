/** 
 * Prompt the user for a new file to the sketch, then call the other addFile() function to actually add it.
 */
public void handleAddFile(){
  ensureExistence();
  if (isReadOnly()) {
    Messages.showMessage(Language.text("add_file.messages.is_read_only"),Language.text("add_file.messages.is_read_only.description"));
    return;
  }
  String prompt=Language.text("file");
  FileDialog fd=new FileDialog(editor,prompt,FileDialog.LOAD);
  fd.setVisible(true);
  String directory=fd.getDirectory();
  String filename=fd.getFile();
  if (filename == null)   return;
  File sourceFile=new File(directory,filename);
  boolean result=addFile(sourceFile);
  if (result) {
  }
}
