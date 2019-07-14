/** 
 * Prompt a dialog to user to input directory name
 * @param path     current path at which directory to create
 * @param ma       {@link MainFragment} current fragment
 */
void mkdir(final OpenMode openMode,final String path,final MainFragment ma){
  mk(R.string.newfolder,"",(dialog,which) -> {
    EditText textfield=dialog.getCustomView().findViewById(R.id.singleedittext_input);
    mkDir(new HybridFile(openMode,path + "/" + textfield.getText().toString()),ma);
    dialog.dismiss();
  }
,(text) -> {
    boolean isValidFilename=FileUtil.isValidFilename(text);
    if (!isValidFilename) {
      return new WarnableTextInputValidator.ReturnState(WarnableTextInputValidator.ReturnState.STATE_ERROR,R.string.invalid_name);
    }
 else     if (text.length() < 1) {
      return new WarnableTextInputValidator.ReturnState(WarnableTextInputValidator.ReturnState.STATE_ERROR,R.string.field_empty);
    }
    return new WarnableTextInputValidator.ReturnState();
  }
);
}
