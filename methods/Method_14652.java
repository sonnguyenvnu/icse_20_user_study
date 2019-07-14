public void mergeEmptyUserMetadata(ProjectMetadata metadata){
  if (metadata == null)   return;
  ArrayNode userMetadataPreference=null;
  ArrayNode jsonObjArray=metadata.getUserMetadata();
  initDisplay(jsonObjArray);
  String userMeta=(String)_preferenceStore.get(PreferenceStore.USER_METADATA_KEY);
  if (userMeta == null)   return;
  userMetadataPreference=ParsingUtilities.mapper.createArrayNode();
  for (int index=0; index < userMetadataPreference.size(); index++) {
    boolean found=false;
    ObjectNode placeHolderJsonObj=(ObjectNode)userMetadataPreference.get(index);
    if (!isValidUserMetadataDefinition(placeHolderJsonObj)) {
      logger.warn("Skipped invalid user metadata definition" + placeHolderJsonObj.toString());
      continue;
    }
    for (int i=0; i < jsonObjArray.size(); i++) {
      JsonNode jsonObj=jsonObjArray.get(i);
      if (!(jsonObj instanceof ObjectNode)) {
        continue;
      }
      ObjectNode node=(ObjectNode)jsonObj;
      if (node.get("name").asText("").equals(placeHolderJsonObj.get("name").asText(""))) {
        found=true;
        node.put("display",placeHolderJsonObj.get("display"));
        break;
      }
    }
    if (!found) {
      placeHolderJsonObj.put("value","");
      metadata.getUserMetadata().add(placeHolderJsonObj);
      logger.info("Put the placeholder {} for project {}",placeHolderJsonObj.get("name").asText(""),metadata.getName());
    }
  }
}
