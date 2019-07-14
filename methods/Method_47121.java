private void setFTPPassword(String password){
  try {
    mainActivity.getPrefs().edit().putString(FtpService.KEY_PREFERENCE_PASSWORD,CryptUtil.encryptPassword(getContext(),password)).apply();
  }
 catch (  GeneralSecurityException|IOException e) {
    e.printStackTrace();
    Toast.makeText(getContext(),getResources().getString(R.string.error),Toast.LENGTH_LONG).show();
  }
  updateStatus();
}
