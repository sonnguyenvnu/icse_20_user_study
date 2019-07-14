protected String getOptionalParm(JobDataMap data,String property){
  String value=data.getString(property);
  if ((value != null) && (value.trim().length() == 0)) {
    return null;
  }
  return value;
}
