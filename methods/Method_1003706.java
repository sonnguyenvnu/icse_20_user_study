/** 
 * Creates a service that executes the given action as the  {@link #onStop(StopEvent)} implementation.<p> This can be used to of a service implementation from a lambda expression, instead of creating an anonymous impl of  {@code Service}.
 * @param name the name of the service
 * @param action the action to execute on stop
 * @return the service implementation
 * @since 1.4
 */
static Service shutdown(String name,Action<? super StopEvent> action){
  return new Service(){
    @Override public String getName(){
      return name;
    }
    @Override public void onStop(    StopEvent event) throws Exception {
      action.execute(event);
    }
  }
;
}
