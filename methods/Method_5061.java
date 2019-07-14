@SuppressWarnings("MissingPermission") private static JobInfo buildJobInfo(int jobId,ComponentName jobServiceComponentName,Requirements requirements,String serviceAction,String servicePackage){
  JobInfo.Builder builder=new JobInfo.Builder(jobId,jobServiceComponentName);
  int networkType;
switch (requirements.getRequiredNetworkType()) {
case Requirements.NETWORK_TYPE_NONE:
    networkType=JobInfo.NETWORK_TYPE_NONE;
  break;
case Requirements.NETWORK_TYPE_ANY:
networkType=JobInfo.NETWORK_TYPE_ANY;
break;
case Requirements.NETWORK_TYPE_UNMETERED:
networkType=JobInfo.NETWORK_TYPE_UNMETERED;
break;
case Requirements.NETWORK_TYPE_NOT_ROAMING:
if (Util.SDK_INT >= 24) {
networkType=JobInfo.NETWORK_TYPE_NOT_ROAMING;
}
 else {
throw new UnsupportedOperationException();
}
break;
case Requirements.NETWORK_TYPE_METERED:
if (Util.SDK_INT >= 26) {
networkType=JobInfo.NETWORK_TYPE_METERED;
}
 else {
throw new UnsupportedOperationException();
}
break;
default :
throw new UnsupportedOperationException();
}
builder.setRequiredNetworkType(networkType);
builder.setRequiresDeviceIdle(requirements.isIdleRequired());
builder.setRequiresCharging(requirements.isChargingRequired());
builder.setPersisted(true);
PersistableBundle extras=new PersistableBundle();
extras.putString(KEY_SERVICE_ACTION,serviceAction);
extras.putString(KEY_SERVICE_PACKAGE,servicePackage);
extras.putInt(KEY_REQUIREMENTS,requirements.getRequirements());
builder.setExtras(extras);
return builder.build();
}
