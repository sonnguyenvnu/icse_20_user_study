public static void authenticateUser(final ThemedActivity activity,final AuthCallBack passwordInterface){
  AlertDialog.Builder builder=new AlertDialog.Builder(activity,activity.getDialogStyle());
  CancellationSignal mCancellationSignal=new CancellationSignal();
  final View view=activity.getLayoutInflater().inflate(org.horaapps.leafpic.R.layout.dialog_password,null);
  final TextView passwordDialogTitle=view.findViewById(org.horaapps.leafpic.R.id.password_dialog_title);
  final CardView passwordDialogCard=view.findViewById(org.horaapps.leafpic.R.id.password_dialog_card);
  final EditText editTextPassword=view.findViewById(org.horaapps.leafpic.R.id.password_edittxt);
  final ThemedIcon fingerprintIcon=view.findViewById(R.id.fingerprint_icon);
  passwordDialogTitle.setBackgroundColor(activity.getPrimaryColor());
  passwordDialogCard.setBackgroundColor(activity.getCardBackgroundColor());
  ThemeHelper.setCursorColor(editTextPassword,activity.getTextColor());
  editTextPassword.getBackground().mutate().setColorFilter(activity.getTextColor(),PorterDuff.Mode.SRC_ATOP);
  editTextPassword.setTextColor(activity.getTextColor());
  fingerprintIcon.setColor(activity.getIconColor());
  builder.setView(view);
  builder.setPositiveButton(activity.getString(R.string.ok_action).toUpperCase(),(dialog,which) -> {
  }
);
  builder.setNegativeButton(activity.getString(R.string.cancel).toUpperCase(),(dialog,which) -> hideKeyboard(activity,editTextPassword.getWindowToken()));
  final AlertDialog dialog=builder.create();
  dialog.show();
  showKeyboard(activity);
  editTextPassword.requestFocus();
  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && Security.isFingerprintUsed()) {
    FingerprintHandler fingerprintHandler=new FingerprintHandler(activity,mCancellationSignal);
    if (fingerprintHandler.isFingerprintSupported()) {
      fingerprintHandler.setOnFingerprintResult(new FingerprintHandler.CallBack(){
        @Override public void onSuccess(){
          hideKeyboard(activity,editTextPassword.getWindowToken());
          dialog.dismiss();
          passwordInterface.onAuthenticated();
        }
        @Override public void onError(        String s){
        }
      }
);
      fingerprintHandler.startAuth();
    }
 else {
      fingerprintIcon.setVisibility(View.GONE);
    }
  }
 else {
    fingerprintIcon.setVisibility(View.GONE);
  }
  dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
    if (checkPassword(editTextPassword.getText().toString())) {
      hideKeyboard(activity,editTextPassword.getWindowToken());
      mCancellationSignal.cancel();
      dialog.dismiss();
      passwordInterface.onAuthenticated();
    }
 else {
      editTextPassword.getText().clear();
      editTextPassword.requestFocus();
      passwordInterface.onError();
    }
  }
);
}
