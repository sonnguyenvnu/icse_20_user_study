@Override protected void init(@Nullable Bundle savedInstanceState){
  super.init(savedInstanceState);
  if (getSupportFragmentManager().findFragmentByTag(TAG_ERROR_DIALOG) == null) {
    ErrorReportAlertDialog errDialog=ErrorReportAlertDialog.newInstance(getApplicationContext(),getString(R.string.error),getString(R.string.app_error_occurred),Log.getStackTraceString(getException()),this);
    errDialog.show(getSupportFragmentManager(),TAG_ERROR_DIALOG);
  }
}
