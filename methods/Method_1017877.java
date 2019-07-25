@Override public Alert.ID resolve(Serializable ser){
  if (ser instanceof String) {
    return new AlertID((String)ser);
  }
 else   if (ser instanceof UUID) {
    return new AlertID((UUID)ser);
  }
 else   if (ser instanceof AlertID) {
    return (AlertID)ser;
  }
 else {
    throw new IllegalArgumentException("Invalid ID source format: " + ser.getClass());
  }
}
