private void setPasswordDialog(){
  final AlertDialog.Builder passwordDialog=new AlertDialog.Builder(SecurityActivity.this,getDialogStyle());
  final View PasswordDialogLayout=getLayoutInflater().inflate(org.horaapps.leafpic.R.layout.dialog_set_password,null);
  final TextView passwordDialogTitle=(TextView)PasswordDialogLayout.findViewById(org.horaapps.leafpic.R.id.password_dialog_title);
  final CardView passwordDialogCard=(CardView)PasswordDialogLayout.findViewById(org.horaapps.leafpic.R.id.password_dialog_card);
  final EditText editTextPassword=(EditText)PasswordDialogLayout.findViewById(org.horaapps.leafpic.R.id.password_edittxt);
  final EditText editTextConfirmPassword=(EditText)PasswordDialogLayout.findViewById(org.horaapps.leafpic.R.id.confirm_password_edittxt);
  passwordDialogTitle.setBackgroundColor(getPrimaryColor());
  passwordDialogCard.setBackgroundColor(getCardBackgroundColor());
  editTextPassword.getBackground().mutate().setColorFilter(getTextColor(),PorterDuff.Mode.SRC_ATOP);
  editTextPassword.setTextColor(getTextColor());
  editTextPassword.setHintTextColor(getSubTextColor());
  ThemeHelper.setCursorColor(editTextPassword,getTextColor());
  editTextConfirmPassword.getBackground().mutate().setColorFilter(getTextColor(),PorterDuff.Mode.SRC_ATOP);
  editTextConfirmPassword.setTextColor(getTextColor());
  editTextConfirmPassword.setHintTextColor(getSubTextColor());
  ThemeHelper.setCursorColor(editTextConfirmPassword,getTextColor());
  passwordDialog.setView(PasswordDialogLayout);
  AlertDialog dialog=passwordDialog.create();
  dialog.setCancelable(false);
  dialog.setButton(DialogInterface.BUTTON_POSITIVE,getString(org.horaapps.leafpic.R.string.cancel).toUpperCase(),new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialog,    int which){
      toggleResetSecurity();
    }
  }
);
  dialog.setButton(DialogInterface.BUTTON_NEGATIVE,getString(org.horaapps.leafpic.R.string.ok_action).toUpperCase(),new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialog,    int which){
      if (editTextPassword.length() > 3) {
        if (editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())) {
          if (Security.setPassword(editTextPassword.getText().toString())) {
            swActiveSecurity.setChecked(true);
            toggleEnabledChild(true);
            Toast.makeText(getApplicationContext(),org.horaapps.leafpic.R.string.remember_password_message,Toast.LENGTH_SHORT).show();
          }
 else {
            Toast.makeText(SecurityActivity.this,R.string.error_contact_developer,Toast.LENGTH_SHORT).show();
            toggleResetSecurity();
          }
        }
 else {
          Toast.makeText(getApplicationContext(),org.horaapps.leafpic.R.string.password_dont_match,Toast.LENGTH_SHORT).show();
          toggleResetSecurity();
        }
      }
 else {
        Toast.makeText(getApplicationContext(),org.horaapps.leafpic.R.string.error_password_length,Toast.LENGTH_SHORT).show();
        toggleResetSecurity();
      }
    }
  }
);
  dialog.show();
}
