public String getDeviceInfo(){
  if (context == null)   return "null context\n";
  WindowManager wm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
  return String.format("App Version Name: %s\n",BuildConfig.VERSION_NAME) + String.format("App Version Code: %s\n",BuildConfig.VERSION_CODE) + String.format("OS Version: %s (%s)\n",System.getProperty("os.version"),Build.VERSION.INCREMENTAL) + String.format("OS API Level: %s\n",Build.VERSION.SDK) + String.format("Device: %s\n",Build.DEVICE) + String.format("Model (Product): %s (%s)\n",Build.MODEL,Build.PRODUCT) + String.format("Manufacturer: %s\n",Build.MANUFACTURER) + String.format("Other tags: %s\n",Build.TAGS) + String.format("Screen Width: %s\n",wm.getDefaultDisplay().getWidth()) + String.format("Screen Height: %s\n",wm.getDefaultDisplay().getHeight()) + String.format("External storage state: %s\n\n",Environment.getExternalStorageState());
}
