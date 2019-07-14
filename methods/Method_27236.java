public static void clearPurchases(){
  PrefHelper.set(PRO_ITEMS,false);
  PrefHelper.set(BLUISH_THEME_ENABLED,false);
  PrefHelper.set(AMLOD_THEME_ENABLED,false);
  setEnterpriseUrl(null);
}
