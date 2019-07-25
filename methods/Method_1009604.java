synchronized public void fire(PropertyChangeSupport propertyChangeSupport){
  String lastChanges=toString();
  if (lastChanges != null && lastChanges.length() > 0) {
    propertyChangeSupport.firePropertyChange("LastChange",previousValue,lastChanges);
    reset();
  }
}
