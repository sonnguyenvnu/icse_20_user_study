@Override public WritableModelProperty init(){
  super.init();
  addHandler(new EventHandler<PropertyChangeEvent<T>>(){
    public void onEvent(    PropertyChangeEvent<T> event){
      safeSetModelPropertyValue(event.getNewValue());
    }
  }
);
  return this;
}
