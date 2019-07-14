public static String getFastHubIssueTemplate(boolean enterprise){
  String brand=(!isEmulator()) ? Build.BRAND : "Android Emulator";
  String model=(!isEmulator()) ? DeviceNameGetter.getInstance().getDeviceName() : "Android Emulator";
  StringBuilder builder=new StringBuilder().append("**FastHub Version: ").append(BuildConfig.VERSION_NAME).append(enterprise ? " Enterprise**" : "**").append("  \n").append(!isInstalledFromPlaySore(App.getInstance()) ? "**APK Source: Unknown**  \n" : "").append("**Android Version: ").append(String.valueOf(Build.VERSION.RELEASE)).append(" (SDK: ").append(String.valueOf(Build.VERSION.SDK_INT)).append(")**").append("  \n").append("**Device Information:**").append("  \n").append("- **").append(!model.equalsIgnoreCase(brand) ? "Manufacturer" : "Manufacturer&Brand").append(":** ").append(Build.MANUFACTURER).append("  \n");
  if (!(model.equalsIgnoreCase(brand) || "google".equals(Build.BRAND))) {
    builder.append("- **Brand:** ").append(brand).append("  \n");
  }
  builder.append("- **Model:** ").append(model).append("  \n").append("---").append("\n\n");
  if (!Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
    builder.append("<!--").append(App.getInstance().getString(R.string.english_please)).append("-->").append("\n");
  }
  return builder.toString();
}
