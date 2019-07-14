/** 
 * Show dialog to rename a file
 * @param f the file to rename
 */
public void rename(final HybridFileParcelable f){
  MaterialDialog renameDialog=GeneralDialogCreation.showNameDialog(getMainActivity(),"",f.getName(),getResources().getString(R.string.rename),getResources().getString(R.string.save),null,getResources().getString(R.string.cancel),(dialog,which) -> {
    EditText textfield=dialog.getCustomView().findViewById(R.id.singleedittext_input);
    String name1=textfield.getText().toString();
    if (f.isSmb()) {
      if (f.isDirectory() && !name1.endsWith("/"))       name1=name1 + "/";
    }
    getMainActivity().mainActivityHelper.rename(openMode,f.getPath(),CURRENT_PATH + "/" + name1,getActivity(),getMainActivity().isRootExplorer());
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
  EditText textfield=renameDialog.getCustomView().findViewById(R.id.singleedittext_input);
  textfield.post(() -> {
    if (!f.isDirectory()) {
      textfield.setSelection(f.getNameString(getContext()).length());
    }
  }
);
}
