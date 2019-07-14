@Override public void onApplicationEvent(ContextClosedEvent event){
  System.out.println("ContextClosedEvent: " + event.getApplicationContext().getId());
}
