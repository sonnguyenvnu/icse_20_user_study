private Collection<String> getAudience(Map<String,?> map){
  Object auds=map.get(AUD);
  if (auds instanceof Collection) {
    @SuppressWarnings("unchecked") Collection<String> result=(Collection<String>)auds;
    return result;
  }
  return Collections.singleton((String)auds);
}
