private void showUnsupportedExceptionDialog(){
  new AlertDialog.Builder(Home.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.device_not_supported)).setMessage(getString(R.string.device_not_supported_message)).setCancelable(false).setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialog,    int which){
      Home.this.finish();
    }
  }
).create().show();
}
