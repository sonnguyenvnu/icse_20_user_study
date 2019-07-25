protected void load(){
  if (delegate.get() == null) {
    values=defaultValues;
  }
 else {
    values=transform(split(delegate.get()));
  }
}
