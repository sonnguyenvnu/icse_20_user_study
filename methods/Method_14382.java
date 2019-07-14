@JsonIgnore public ObjectNode getRetrievalRecord(){
synchronized (config) {
    return JSONUtilities.getObject(config,"retrievalRecord");
  }
}
