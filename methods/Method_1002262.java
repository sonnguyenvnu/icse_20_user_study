private void setup(String propName,Set<T> defaultValue,Splitter splitter){
  this.defaultValues=(defaultValue == null ? null : Collections.unmodifiableSet(new LinkedHashSet<T>(defaultValue)));
  this.splitter=splitter;
  delegate=DynamicPropertyFactory.getInstance().getStringProperty(propName,null);
  load();
  Runnable callback=new Runnable(){
    @Override public void run(){
      propertyChangedInternal();
    }
  }
;
  delegate.addCallback(callback);
  callbackList.add(callback);
}
