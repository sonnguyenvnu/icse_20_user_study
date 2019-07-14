@Override public void onShow(DialogInterface dialog){
  resizeDialogSize();
  if (editTextName == null) {
    editTextName=(EditText)getDialog().findViewById(R.id.edit_text);
    editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener(){
      @Override public boolean onEditorAction(      TextView v,      int actionId,      KeyEvent event){
        if (actionId == EditorInfo.IME_ACTION_DONE) {
          if (editTextName.length() > 0) {
            onConfirm();
          }
          return true;
        }
        return false;
      }
    }
);
  }
  if (isEditMode()) {
    editTextName.setText(mPlayList.getName());
  }
  editTextName.requestFocus();
  editTextName.setSelection(editTextName.length());
}
