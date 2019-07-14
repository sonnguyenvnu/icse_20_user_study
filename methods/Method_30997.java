@NonNull public static Intent makeViewAppInMarket(@NonNull String packageName){
  return makeView(Uri.parse("market://details?id=" + packageName));
}
