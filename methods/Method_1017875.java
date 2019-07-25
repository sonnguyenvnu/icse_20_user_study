@Override public ID resolve(Serializable value){
  if (value instanceof String) {
    return SourceAlertID.create((String)value,this.sources,this.managers);
  }
 else   if (value instanceof SourceAlertID) {
    return (SourceAlertID)value;
  }
 else {
    throw new IllegalArgumentException("Unrecognized alert ID format: " + value);
  }
}
