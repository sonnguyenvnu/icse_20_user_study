protected String getRequiredParm(JobDataMap data,String property,String constantName){
  String value=getOptionalParm(data,property);
  if (value == null) {
    throw new IllegalArgumentException(constantName + " not specified.");
  }
  return value;
}
