@Override public JsonResult execute(){
  DataBundle dataBundle=JsonUtils.fromJson(getRequestBody(),DataBundle.class);
  try {
    logic.persistDataBundle(dataBundle);
  }
 catch (  InvalidParametersException e) {
    return new JsonResult("Error when persisting data bundle: " + e.getMessage(),HttpStatus.SC_INTERNAL_SERVER_ERROR);
  }
  return new JsonResult("Data bundle successfully persisted.",HttpStatus.SC_OK);
}
