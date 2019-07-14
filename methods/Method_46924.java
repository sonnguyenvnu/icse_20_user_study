@Override protected void onProgressUpdate(IOException... values){
  super.onProgressUpdate(values);
  if (values.length < 1)   return;
  IOException result=values[0];
  MaterialDialog.Builder builder=new MaterialDialog.Builder(AppConfig.getInstance().getMainActivityContext());
  View dialogLayout=View.inflate(AppConfig.getInstance().getMainActivityContext(),R.layout.dialog_singleedittext,null);
  WarnableTextInputLayout wilTextfield=dialogLayout.findViewById(R.id.singleedittext_warnabletextinputlayout);
  EditText textfield=dialogLayout.findViewById(R.id.singleedittext_input);
  textfield.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
  builder.customView(dialogLayout,false).autoDismiss(false).title(R.string.ssh_key_prompt_passphrase).positiveText(R.string.ok).onPositive(((dialog,which) -> {
    this.passwordFinder=new PasswordFinder(){
      @Override public char[] reqPassword(      Resource<?> resource){
        return textfield.getText().toString().toCharArray();
      }
      @Override public boolean shouldRetry(      Resource<?> resource){
        return false;
      }
    }
;
    this.paused=false;
    dialog.dismiss();
  }
)).negativeText(R.string.cancel).onNegative(((dialog,which) -> {
    dialog.dismiss();
    toastOnParseError(result);
    cancel(true);
  }
));
  MaterialDialog dialog=builder.show();
  new WarnableTextInputValidator(AppConfig.getInstance().getMainActivityContext(),textfield,wilTextfield,dialog.getActionButton(DialogAction.POSITIVE),(text) -> {
    if (text.length() < 1) {
      return new WarnableTextInputValidator.ReturnState(WarnableTextInputValidator.ReturnState.STATE_ERROR,R.string.field_empty);
    }
    return new WarnableTextInputValidator.ReturnState();
  }
);
  if (errorMessage != null) {
    wilTextfield.setError(errorMessage);
    textfield.selectAll();
  }
}
