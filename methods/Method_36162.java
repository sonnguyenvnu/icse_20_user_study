@JsonIgnore public boolean isNoExactMatch(){
  return !responseDefinition.wasConfigured();
}
