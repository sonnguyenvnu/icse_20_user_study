private SharedPreferences getPreferences(){
  if (currentAccount == 0) {
    return ApplicationLoader.applicationContext.getSharedPreferences("userconfing",Context.MODE_PRIVATE);
  }
 else {
    return ApplicationLoader.applicationContext.getSharedPreferences("userconfig" + currentAccount,Context.MODE_PRIVATE);
  }
}
