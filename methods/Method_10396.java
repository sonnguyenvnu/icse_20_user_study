private void showLoggingVerbosityDialog(){
  AlertDialog ad=new AlertDialog.Builder(this).setTitle("Set Logging Verbosity").setSingleChoiceItems(new String[]{"VERBOSE","DEBUG","INFO","WARN","ERROR","WTF"},getAsyncHttpClient().getLoggingLevel() - 2,new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialog,    int which){
      getAsyncHttpClient().setLoggingLevel(which + 2);
      dialog.dismiss();
    }
  }
).setCancelable(true).setNeutralButton("Cancel",null).create();
  ad.show();
}
