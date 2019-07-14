@Override public void onApplicationEvent(ContextClosedEvent event){
  System.out.println("AfterContextClosedEvent: " + event.getApplicationContext().getId());
}
