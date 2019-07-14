private void populateVersionAndDeviceDetails(){
  final Preference preferenceAndroidVersion=findPreference(KEY_DETAILS_ANDROID_VERSION);
  final String androidVersion=getString(R.string.preference_details_android_version_summary,String.valueOf(Build.VERSION.SDK_INT),Build.VERSION.RELEASE);
  preferenceAndroidVersion.setSummary(androidVersion);
  final Preference preferenceCpuArchitecture=findPreference(KEY_DETAILS_CPU_ARCHITECTURE);
  final String cpuArch=System.getProperty("os.arch");
  final String cpuDetails;
  if (Build.VERSION.SDK_INT < 21) {
    cpuDetails=getString(R.string.preference_details_cpu_architecture_summary_before_21,cpuArch,Build.CPU_ABI,Build.CPU_ABI2);
  }
 else {
    cpuDetails=getString(R.string.preference_details_cpu_architecture_summary_after_21,cpuArch,Arrays.toString(Build.SUPPORTED_ABIS));
  }
  preferenceCpuArchitecture.setSummary(cpuDetails);
  final Preference preferenceDeviceName=findPreference(KEY_DETAILS_DEVICE_NAME);
  final String deviceName=getString(R.string.preference_details_device_name_summary,Build.MANUFACTURER,Build.DEVICE);
  preferenceDeviceName.setSummary(deviceName);
}
