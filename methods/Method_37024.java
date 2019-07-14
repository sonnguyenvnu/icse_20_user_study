@Override public BaseLayoutBinder create(String type){
  if (mDelegate.hasType(type)) {
    return new BaseLayoutBinder();
  }
  return null;
}
