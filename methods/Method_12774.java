public static final boolean isLocalService(final ServiceInfo serviceInfo){
  return TextUtils.isEmpty(serviceInfo.processName) || serviceInfo.applicationInfo.packageName.equals(serviceInfo.processName);
}
