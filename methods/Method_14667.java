public void setAnyField(String metaName,String valueString){
  Class<? extends ProjectMetadata> metaClass=this.getClass();
  try {
    Field metaField=metaClass.getDeclaredField("_" + metaName);
    if (metaName.equals("tags")) {
      metaField.set(this,valueString.split(","));
    }
 else {
      metaField.set(this,valueString);
    }
  }
 catch (  NoSuchFieldException e) {
    updateUserMetadata(metaName,valueString);
  }
catch (  SecurityException|IllegalArgumentException|IllegalAccessException e) {
    logger.error(ExceptionUtils.getFullStackTrace(e));
  }
}
