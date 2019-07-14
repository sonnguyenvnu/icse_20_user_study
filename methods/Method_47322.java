public static void shareCloudFile(String path,final OpenMode openMode,final Context context){
  new AsyncTask<String,Void,String>(){
    @Override protected String doInBackground(    String... params){
      String shareFilePath=params[0];
      CloudStorage cloudStorage=DataUtils.getInstance().getAccount(openMode);
      return cloudStorage.createShareLink(CloudUtil.stripPath(openMode,shareFilePath));
    }
    @Override protected void onPostExecute(    String s){
      super.onPostExecute(s);
      FileUtils.copyToClipboard(context,s);
      Toast.makeText(context,context.getString(R.string.cloud_share_copied),Toast.LENGTH_LONG).show();
    }
  }
.execute(path);
}
