/** 
 * Handler for Sketch &rarr; Export Application
 */
public void handleExportApplication(){
  if (handleExportCheckModified()) {
    statusNotice(Language.text("export.notice.exporting"));
    try {
      if (exportApplicationPrompt()) {
        Platform.openFolder(sketch.getFolder());
        statusNotice(Language.text("export.notice.exporting.done"));
      }
 else {
      }
    }
 catch (    Exception e) {
      statusNotice(Language.text("export.notice.exporting.error"));
      e.printStackTrace();
    }
  }
}
