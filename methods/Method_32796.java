public String getInstallReferrer(){
  SharedPreferences sharedPref=getReactApplicationContext().getSharedPreferences("react-native-device-info",Context.MODE_PRIVATE);
  return sharedPref.getString("installReferrer",null);
}
