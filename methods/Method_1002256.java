private void setup(String propName,List<T> defaultValue,Splitter splitter){
  this.defaultValues=(defaultValue == null ? null : Collections.unmodifiableList(new ArrayList<T>(defaultValue)));
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
}
