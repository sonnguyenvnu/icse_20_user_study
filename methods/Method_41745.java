public static boolean isDestinationSecure(final JobDataMap dataMap){
  return ((dataMap.getString(JmsHelper.JMS_USER) != null) && (dataMap.getString(JmsHelper.JMS_PASSWORD) != null));
}
