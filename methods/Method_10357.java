/** 
 * Displays a dialog showing contents of `custom_ca.txt` file from assets. This is needed to avoid Lint's strict mode.
 */
private void showCustomCAHelp(){
  AlertDialog.Builder builder=new AlertDialog.Builder(this);
  builder.setTitle(R.string.title_custom_ca);
  builder.setMessage(getReadmeText());
  builder.setNeutralButton(android.R.string.cancel,new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialog,    int which){
      dialog.dismiss();
    }
  }
);
  builder.show();
}
