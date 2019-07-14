@SuppressLint({"MissingPermission","HardwareIds"}) private Map<String,Object> generateConstants(){
  HashMap<String,Object> constants=new HashMap<String,Object>();
  PackageManager packageManager=this.reactContext.getPackageManager();
  String packageName=this.reactContext.getPackageName();
  constants.put("appVersion","not available");
  constants.put("appName","not available");
  constants.put("buildVersion","not available");
  constants.put("buildNumber",0);
  try {
    PackageInfo packageInfo=packageManager.getPackageInfo(packageName,0);
    PackageInfo info=packageManager.getPackageInfo(packageName,0);
    String applicationName=this.reactContext.getApplicationInfo().loadLabel(this.reactContext.getPackageManager()).toString();
    constants.put("appVersion",info.versionName);
    constants.put("buildNumber",info.versionCode);
    constants.put("firstInstallTime",info.firstInstallTime);
    constants.put("lastUpdateTime",info.lastUpdateTime);
    constants.put("appName",applicationName);
  }
 catch (  PackageManager.NameNotFoundException e) {
    e.printStackTrace();
  }
  String deviceName="Unknown";
  String permission="android.permission.BLUETOOTH";
  int res=this.reactContext.checkCallingOrSelfPermission(permission);
  if (res == PackageManager.PERMISSION_GRANTED) {
    try {
      BluetoothAdapter myDevice=BluetoothAdapter.getDefaultAdapter();
      if (myDevice != null) {
        deviceName=myDevice.getName();
      }
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  try {
    if (Class.forName("com.google.android.gms.iid.InstanceID") != null) {
      constants.put("instanceId",com.google.android.gms.iid.InstanceID.getInstance(this.reactContext).getId());
    }
  }
 catch (  ClassNotFoundException e) {
    constants.put("instanceId","N/A: Add com.google.android.gms:play-services-gcm to your project.");
  }
  constants.put("serialNumber",Build.SERIAL);
  constants.put("deviceName",deviceName);
  constants.put("systemName","Android");
  constants.put("systemVersion",Build.VERSION.RELEASE);
  constants.put("model",Build.MODEL);
  constants.put("brand",Build.BRAND);
  constants.put("buildId",Build.ID);
  constants.put("deviceId",Build.BOARD);
  constants.put("apiLevel",Build.VERSION.SDK_INT);
  constants.put("bootloader",Build.BOOTLOADER);
  constants.put("device",Build.DEVICE);
  constants.put("display",Build.DISPLAY);
  constants.put("fingerprint",Build.FINGERPRINT);
  constants.put("hardware",Build.HARDWARE);
  constants.put("host",Build.HOST);
  constants.put("product",Build.PRODUCT);
  constants.put("tags",Build.TAGS);
  constants.put("type",Build.TYPE);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    constants.put("baseOS",Build.VERSION.BASE_OS);
    constants.put("previewSdkInt",Build.VERSION.PREVIEW_SDK_INT);
    constants.put("securityPatch",Build.VERSION.SECURITY_PATCH);
  }
  constants.put("codename",Build.VERSION.CODENAME);
  constants.put("incremental",Build.VERSION.INCREMENTAL);
  constants.put("deviceLocale",this.getCurrentLanguage());
  constants.put("preferredLocales",this.getPreferredLocales());
  constants.put("deviceCountry",this.getCurrentCountry());
  constants.put("uniqueId",Settings.Secure.getString(this.reactContext.getContentResolver(),Settings.Secure.ANDROID_ID));
  constants.put("systemManufacturer",Build.MANUFACTURER);
  constants.put("bundleId",packageName);
  try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      constants.put("userAgent",WebSettings.getDefaultUserAgent(this.reactContext));
    }
 else {
      constants.put("userAgent",System.getProperty("http.agent"));
    }
  }
 catch (  RuntimeException e) {
    constants.put("userAgent",System.getProperty("http.agent"));
  }
  constants.put("timezone",TimeZone.getDefault().getID());
  constants.put("isEmulator",this.isEmulator());
  constants.put("isTablet",this.isTablet());
  constants.put("fontScale",this.fontScale());
  constants.put("is24Hour",this.is24Hour());
  constants.put("carrier",this.getCarrier());
  constants.put("totalDiskCapacity",this.getTotalDiskCapacity());
  constants.put("freeDiskStorage",this.getFreeDiskStorage());
  constants.put("installReferrer",this.getInstallReferrer());
  if (reactContext != null && (reactContext.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && reactContext.checkCallingOrSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && reactContext.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED))) {
    TelephonyManager telMgr=(TelephonyManager)this.reactContext.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
    constants.put("phoneNumber",telMgr.getLine1Number());
  }
 else {
    constants.put("phoneNumber",null);
  }
  Runtime rt=Runtime.getRuntime();
  constants.put("maxMemory",rt.maxMemory());
  ActivityManager actMgr=(ActivityManager)this.reactContext.getSystemService(Context.ACTIVITY_SERVICE);
  ActivityManager.MemoryInfo memInfo=new ActivityManager.MemoryInfo();
  actMgr.getMemoryInfo(memInfo);
  constants.put("totalMemory",memInfo.totalMem);
  constants.put("deviceType",deviceType.getValue());
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    constants.put("supportedABIs",Build.SUPPORTED_ABIS);
    constants.put("supported32BitAbis",Arrays.asList(Build.SUPPORTED_32_BIT_ABIS));
    constants.put("supported64BitAbis",Arrays.asList(Build.SUPPORTED_64_BIT_ABIS));
  }
 else {
    constants.put("supportedABIs",new String[]{Build.CPU_ABI});
    constants.put("supported32BitAbis",Arrays.asList(new String[]{}));
    constants.put("supported64BitAbis",Arrays.asList(new String[]{}));
  }
  return constants;
}
