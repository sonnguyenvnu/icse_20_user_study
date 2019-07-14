/** 
 * The call has already checked to make sure this sketch is not modified, now change the mode.
 * @return true if mode is changed.
 */
public boolean changeMode(Mode mode){
  Mode oldMode=activeEditor.getMode();
  if (oldMode != mode) {
    Sketch sketch=activeEditor.getSketch();
    nextMode=mode;
    if (sketch.isUntitled()) {
      handleClose(activeEditor,true);
      handleNew();
    }
 else {
      boolean newModeCanHandleCurrentSource=true;
      for (      final SketchCode code : sketch.getCode()) {
        if (!mode.validExtension(code.getExtension())) {
          newModeCanHandleCurrentSource=false;
          break;
        }
      }
      if (!newModeCanHandleCurrentSource) {
        return false;
      }
 else {
        final File props=new File(sketch.getCodeFolder(),"sketch.properties");
        saveModeSettings(props,nextMode);
        handleClose(activeEditor,true);
        Editor editor=handleOpen(sketch.getMainFilePath());
        if (editor == null) {
          saveModeSettings(props,oldMode);
          handleOpen(sketch.getMainFilePath());
          return false;
        }
      }
    }
  }
  return true;
}
