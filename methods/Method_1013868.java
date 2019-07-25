@Modified protected void modified(Map<String,Object> properties){
  if (properties == null) {
    realmName=null;
    return;
  }
  Object propertyValue=properties.get("realmName");
  if (propertyValue != null) {
    if (propertyValue instanceof String) {
      realmName=(String)propertyValue;
    }
 else {
      realmName=propertyValue.toString();
    }
  }
 else {
    realmName=null;
  }
}
