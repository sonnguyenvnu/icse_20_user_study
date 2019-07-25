@Override public Object process(Map.Entry<String,MapSession> entry){
  MapSession value=entry.getValue();
  if (value == null) {
    return Boolean.FALSE;
  }
  if (this.lastAccessedTime != null) {
    value.setLastAccessedTime(this.lastAccessedTime);
  }
  if (this.maxInactiveInterval != null) {
    value.setMaxInactiveInterval(this.maxInactiveInterval);
  }
  if (this.delta != null) {
    for (    final Map.Entry<String,Object> attribute : this.delta.entrySet()) {
      if (attribute.getValue() != null) {
        value.setAttribute(attribute.getKey(),attribute.getValue());
      }
 else {
        value.removeAttribute(attribute.getKey());
      }
    }
  }
  entry.setValue(value);
  return Boolean.TRUE;
}
