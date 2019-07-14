private void disableButtonIfTitleEmpty(final EditText title,final MaterialDialog dialog){
  title.addTextChangedListener(new SimpleTextWatcher(){
    @Override public void afterTextChanged(    Editable s){
      dialog.getActionButton(DialogAction.POSITIVE).setEnabled(title.length() > 0);
    }
  }
);
}
