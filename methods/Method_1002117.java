private boolean checkin(boolean force){
  try {
    CheckinManager.checkin(LoginActivity.this,force);
    return true;
  }
 catch (  IOException e) {
    Log.w(TAG,"Checkin failed",e);
  }
  return false;
}
