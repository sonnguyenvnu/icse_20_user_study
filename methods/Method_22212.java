@SuppressLint("HardwareIds") @RequiresPermission(Manifest.permission.READ_PHONE_STATE) @Override void collect(@NonNull ReportField reportField,@NonNull Context context,@NonNull CoreConfiguration config,@NonNull ReportBuilder reportBuilder,@NonNull CrashReportData target) throws Exception {
  target.put(ReportField.DEVICE_ID,SystemServices.getTelephonyManager(context).getDeviceId());
}
