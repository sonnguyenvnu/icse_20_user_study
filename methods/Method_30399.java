public static void openSearch(Context context){
  if (Settings.PROGRESSIVE_THIRD_PARTY_APP.getValue() && FrodoBridge.search(null,null,null,context)) {
    return;
  }
  UrlHandler.open("https://www.douban.com/search",context);
}
