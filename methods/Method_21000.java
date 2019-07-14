public @NonNull String secureWebProjectUrl(){
  return Uri.parse(webProjectUrl()).buildUpon().scheme("https").build().toString();
}
