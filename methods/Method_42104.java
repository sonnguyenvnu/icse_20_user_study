public ObjectKey getSignature(){
  return new ObjectKey(getDateModified() + getPath() + getOrientation());
}
