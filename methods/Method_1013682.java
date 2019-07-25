@Override default boolean supports(AbstractInstanceEvent event){
  return event instanceof InstanceUp;
}
