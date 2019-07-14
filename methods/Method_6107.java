public static String[] getCurrentKeyboardLanguage(){
  try {
    InputMethodManager inputManager=(InputMethodManager)ApplicationLoader.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    InputMethodSubtype inputMethodSubtype=inputManager.getCurrentInputMethodSubtype();
    String locale=null;
    if (inputMethodSubtype != null) {
      if (Build.VERSION.SDK_INT >= 24) {
        locale=inputMethodSubtype.getLanguageTag();
      }
      if (TextUtils.isEmpty(locale)) {
        locale=inputMethodSubtype.getLocale();
      }
    }
 else {
      inputMethodSubtype=inputManager.getLastInputMethodSubtype();
      if (inputMethodSubtype != null) {
        if (Build.VERSION.SDK_INT >= 24) {
          locale=inputMethodSubtype.getLanguageTag();
        }
        if (TextUtils.isEmpty(locale)) {
          locale=inputMethodSubtype.getLocale();
        }
      }
    }
    if (TextUtils.isEmpty(locale)) {
      locale=LocaleController.getSystemLocaleStringIso639();
      String locale2;
      LocaleController.LocaleInfo localeInfo=LocaleController.getInstance().getCurrentLocaleInfo();
      locale2=localeInfo.getBaseLangCode();
      if (TextUtils.isEmpty(locale2)) {
        locale2=localeInfo.getLangCode();
      }
      if (locale.contains(locale2) || locale2.contains(locale)) {
        if (!locale.contains("en")) {
          locale2="en";
        }
 else {
          locale2=null;
        }
      }
      if (!TextUtils.isEmpty(locale2)) {
        return new String[]{locale.replace('_','-'),locale2};
      }
 else {
        return new String[]{locale.replace('_','-')};
      }
    }
 else {
      return new String[]{locale.replace('_','-')};
    }
  }
 catch (  Exception ignore) {
  }
  return new String[]{"en"};
}
