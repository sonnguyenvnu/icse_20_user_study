@Override public boolean match(OpenElementEvent event){
  if (event == null)   return false;
  if (event == this.open)   return true;
  return event.getURI().equals(getURI()) && event.getName().equals(getName());
}
