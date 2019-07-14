/** 
 * Prompt the user what to do about each tab. Passes the tab to the user's choice of Consumer. Won't let you delete the main tab.
 */
private void showReloadPrompt(List<SketchCode> mergeConflict,List<SketchCode> removed,Consumer<SketchCode> modifiedReload,Consumer<SketchCode> modifiedKeep,Consumer<SketchCode> delete,Consumer<SketchCode> deletedResave){
  for (  SketchCode sc : mergeConflict) {
    if (1 == Messages.showCustomQuestion(editor,Language.text("change_detect.reload.title"),Language.interpolate("change_detect.reload.question",sc.getFileName()),Language.text("change_detect.reload.comment"),0,Language.text("change_detect.button.keep"),Language.text("change_detect.button.load_new"))) {
      modifiedReload.accept(sc);
    }
 else {
      modifiedKeep.accept(sc);
    }
  }
  for (  SketchCode sc : removed) {
    if (!sketch.getCode(0).equals(sc) && 1 == Messages.showCustomQuestion(editor,Language.text("change_detect.delete.title"),Language.interpolate("change_detect.delete.question",sc.getFileName()),Language.text("change_detect.delete.comment"),0,Language.text("change_detect.button.resave"),Language.text("change_detect.button.discard"))) {
      delete.accept(sc);
    }
 else {
      deletedResave.accept(sc);
    }
  }
}
