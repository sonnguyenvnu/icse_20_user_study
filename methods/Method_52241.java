public boolean checksNothing(){
  return getProperty(PACKAGES_DESCRIPTOR).isEmpty() && getProperty(CLASSES_DESCRIPTOR).isEmpty();
}
