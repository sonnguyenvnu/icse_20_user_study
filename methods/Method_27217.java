public static void setAppLangauge(@Nullable String language){
  PrefHelper.set(APP_LANGUAGE,language == null ? "en" : language);
}
