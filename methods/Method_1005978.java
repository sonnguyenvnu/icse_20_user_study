@Override public MapSession apply(Map<String,Object> map){
  Assert.notEmpty(map,"map must not be empty");
  MapSession session=new MapSession(this.sessionId);
  Long creationTime=(Long)map.get(CREATION_TIME_KEY);
  if (creationTime == null) {
    handleMissingKey(CREATION_TIME_KEY);
  }
  session.setCreationTime(Instant.ofEpochMilli(creationTime));
  Long lastAccessedTime=(Long)map.get(LAST_ACCESSED_TIME_KEY);
  if (lastAccessedTime == null) {
    handleMissingKey(LAST_ACCESSED_TIME_KEY);
  }
  session.setLastAccessedTime(Instant.ofEpochMilli(lastAccessedTime));
  Integer maxInactiveInterval=(Integer)map.get(MAX_INACTIVE_INTERVAL_KEY);
  if (maxInactiveInterval == null) {
    handleMissingKey(MAX_INACTIVE_INTERVAL_KEY);
  }
  session.setMaxInactiveInterval(Duration.ofSeconds(maxInactiveInterval));
  map.forEach((name,value) -> {
    if (name.startsWith(ATTRIBUTE_PREFIX)) {
      session.setAttribute(name.substring(ATTRIBUTE_PREFIX.length()),value);
    }
  }
);
  return session;
}
