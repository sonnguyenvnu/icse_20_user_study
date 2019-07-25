private static void send(ResultReceiver resultReceiver,int requestCode,String[] permissions,int[] grantResults){
  Bundle resultData=new Bundle();
  resultData.putStringArray(PERMISSIONS,permissions);
  resultData.putIntArray(GRANT_RESULTS,grantResults);
  resultReceiver.send(requestCode,resultData);
}
