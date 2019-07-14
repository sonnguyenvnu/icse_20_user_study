private boolean checkPlayServices(){
  try {
    int resultCode=GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    return resultCode == ConnectionResult.SUCCESS;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return true;
}
