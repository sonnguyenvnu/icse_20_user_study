/** 
 * Asynctask checks if the item pressed on is a cloud account, and if the token that is saved for it is invalid or not, in which case, we'll clear off the saved token and authenticate the user again
 * @param path the path of item in drawer
 * @param mainActivity reference to main activity to fire callbacks to delete/add connection
 */
public static void checkToken(String path,final MainActivity mainActivity){
  new AsyncTask<String,Void,Boolean>(){
    @Override protected Boolean doInBackground(    String... params){
      boolean isTokenValid=true;
      String path=params[0];
      if (path.startsWith(CloudHandler.CLOUD_PREFIX_DROPBOX)) {
        serviceType=OpenMode.DROPBOX;
        CloudStorage cloudStorageDropbox=dataUtils.getAccount(OpenMode.DROPBOX);
        try {
          cloudStorageDropbox.getUserLogin();
        }
 catch (        Exception e) {
          e.printStackTrace();
          isTokenValid=false;
        }
      }
 else       if (path.startsWith(CloudHandler.CLOUD_PREFIX_ONE_DRIVE)) {
        serviceType=OpenMode.ONEDRIVE;
        CloudStorage cloudStorageOneDrive=dataUtils.getAccount(OpenMode.ONEDRIVE);
        try {
          cloudStorageOneDrive.getUserLogin();
        }
 catch (        Exception e) {
          e.printStackTrace();
          isTokenValid=false;
        }
      }
 else       if (path.startsWith(CloudHandler.CLOUD_PREFIX_BOX)) {
        serviceType=OpenMode.BOX;
        CloudStorage cloudStorageBox=dataUtils.getAccount(OpenMode.BOX);
        try {
          cloudStorageBox.getUserLogin();
        }
 catch (        Exception e) {
          e.printStackTrace();
          isTokenValid=false;
        }
      }
 else       if (path.startsWith(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE)) {
        serviceType=OpenMode.GDRIVE;
        CloudStorage cloudStorageGDrive=dataUtils.getAccount(OpenMode.GDRIVE);
        try {
          cloudStorageGDrive.getUserLogin();
        }
 catch (        Exception e) {
          e.printStackTrace();
          isTokenValid=false;
        }
      }
      return isTokenValid;
    }
    @Override protected void onPostExecute(    Boolean aBoolean){
      super.onPostExecute(aBoolean);
      if (!aBoolean) {
        Toast.makeText(mainActivity,mainActivity.getResources().getString(R.string.cloud_token_lost),Toast.LENGTH_LONG).show();
        mainActivity.deleteConnection(serviceType);
        mainActivity.addConnection(serviceType);
      }
    }
  }
.execute(path);
}
