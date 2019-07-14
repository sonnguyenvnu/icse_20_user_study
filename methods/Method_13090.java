public void fireVisibleDataPropertyChange(){
  if (getAccessibleContext() != null) {
    getAccessibleContext().firePropertyChange("AccessibleVisibleData",false,true);
  }
}
