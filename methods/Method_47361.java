/** 
 * Prompt a dialog to user to input file name
 * @param path     current path at which file to create
 * @param ma       {@link MainFragment} current fragment
 */
void mkfile(final OpenMode openMode,final String path,final MainFragment ma){
  mk(R.string.newfile,NEW_FILE_TXT_EXTENSION,(dialog,which) -> {
    EditText textfield=dialog.getCustomView().findViewById(R.id.singleedittext_input);
    mkFile(new HybridFile(openMode,path + "/" + textfield.getText().toString()),ma);
    dialog.dismiss();
  }
,(text) -> {
    boolean isValidFilename=FileUtil.isValidFilename(text);
    if (isValidFilename && text.length() > 0) {
      SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(mainActivity);
      if (text.startsWith(".") && !prefs.getBoolean(PreferencesConstants.PREFERENCE_SHOW_HIDDENFILES,false)) {
        return new WarnableTextInputValidator.ReturnState(WarnableTextInputValidator.ReturnState.STATE_WARNING,R.string.create_hidden_file_warn);
      }
 else       if (!text.toLowerCase().endsWith(NEW_FILE_TXT_EXTENSION)) {
        return new WarnableTextInputValidator.ReturnState(WarnableTextInputValidator.ReturnState.STATE_WARNING,R.string.create_file_suggest_txt_extension);
      }
    }
 else {
      if (!isValidFilename) {
        return new WarnableTextInputValidator.ReturnState(WarnableTextInputValidator.ReturnState.STATE_ERROR,R.string.invalid_name);
      }
 else       if (text.length() < 1) {
        return new WarnableTextInputValidator.ReturnState(WarnableTextInputValidator.ReturnState.STATE_ERROR,R.string.field_empty);
      }
    }
    return new WarnableTextInputValidator.ReturnState();
  }
);
}
