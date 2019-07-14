public static void setPassportConfig(String json,int hash){
  passportConfigMap=null;
  passportConfigJson=json;
  passportConfigHash=hash;
  saveConfig();
  getCountryLangs();
}
