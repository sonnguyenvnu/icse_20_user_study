@JsonProperty("entries") public void setEntries(JsonNode entries){
  Iterator<String> i=entries.fieldNames();
  while (i.hasNext()) {
    String key=i.next();
    if (entries.get(key) != null) {
      JsonNode o=entries.get(key);
      Object loaded=loadObject(o);
      _prefs.put(key,loaded);
    }
  }
  dirty=false;
}
