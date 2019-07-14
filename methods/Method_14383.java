@JsonIgnore public List<ObjectNode> getSelectedFileRecords(){
  List<ObjectNode> results=new ArrayList<ObjectNode>();
  ObjectNode retrievalRecord=JSONUtilities.getObject(config,"retrievalRecord");
  if (retrievalRecord != null) {
    ArrayNode fileRecordArray=JSONUtilities.getArray(retrievalRecord,"files");
    if (fileRecordArray != null) {
      ArrayNode fileSelectionArray=JSONUtilities.getArray(config,"fileSelection");
      if (fileSelectionArray != null) {
        for (int i=0; i < fileSelectionArray.size(); i++) {
          int index=JSONUtilities.getIntElement(fileSelectionArray,i,-1);
          if (index >= 0 && index < fileRecordArray.size()) {
            results.add(JSONUtilities.getObjectElement(fileRecordArray,index));
          }
        }
      }
    }
  }
  return results;
}
