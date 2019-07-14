public ObjectNode getStoredCredentials(){
  ArrayNode array=(ArrayNode)prefStore.get(PREFERENCE_STORE_KEY);
  if (array != null && array.size() > 0 && array.get(0) instanceof ObjectNode) {
    return (ObjectNode)array.get(0);
  }
  return null;
}
