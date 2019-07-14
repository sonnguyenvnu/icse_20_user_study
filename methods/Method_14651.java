/** 
 * A valid user meta data definition should have name and display property
 * @param placeHolderJsonObj
 * @return
 */
private boolean isValidUserMetadataDefinition(ObjectNode placeHolderJsonObj){
  return (placeHolderJsonObj != null && placeHolderJsonObj.has("name") && placeHolderJsonObj.has("display"));
}
