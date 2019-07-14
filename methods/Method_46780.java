/** 
 * Checks for the action to take when Amaze receives an intent from external source
 */
private void checkForExternalIntent(Intent intent){
  String actionIntent=intent.getAction();
  String type=intent.getType();
  if (actionIntent != null) {
    if (actionIntent.equals(Intent.ACTION_GET_CONTENT)) {
      mReturnIntent=true;
      Toast.makeText(this,getString(R.string.pick_a_file),Toast.LENGTH_LONG).show();
      Utils.disableScreenRotation(this);
    }
 else     if (actionIntent.equals(RingtoneManager.ACTION_RINGTONE_PICKER)) {
      mReturnIntent=true;
      mRingtonePickerIntent=true;
      Toast.makeText(this,getString(R.string.pick_a_file),Toast.LENGTH_LONG).show();
      Utils.disableScreenRotation(this);
    }
 else     if (actionIntent.equals(Intent.ACTION_VIEW)) {
      Uri uri=intent.getData();
      if (type != null && type.equals(ARGS_INTENT_ACTION_VIEW_MIME_FOLDER)) {
        if (uri != null) {
          path=Utils.sanitizeInput(uri.getPath());
        }
 else {
          path=null;
        }
      }
 else {
        openzip=true;
        zippath=Utils.sanitizeInput(uri.toString());
      }
    }
 else     if (actionIntent.equals(Intent.ACTION_SEND) && type != null) {
      Uri uri=intent.getParcelableExtra(Intent.EXTRA_STREAM);
      ArrayList<Uri> uris=new ArrayList<>();
      uris.add(uri);
      initFabToSave(uris);
      Utils.disableScreenRotation(this);
    }
 else     if (actionIntent.equals(Intent.ACTION_SEND_MULTIPLE) && type != null) {
      ArrayList<Uri> arrayList=intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
      initFabToSave(arrayList);
      Utils.disableScreenRotation(this);
    }
  }
}
