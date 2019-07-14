private void disableButtonIfNotPath(EditText path,final MaterialDialog dialog){
  path.addTextChangedListener(new SimpleTextWatcher(){
    @Override public void afterTextChanged(    Editable s){
      dialog.getActionButton(DialogAction.POSITIVE).setEnabled(FileUtils.isPathAccessible(s.toString(),sharedPrefs));
    }
  }
);
}
