/** 
 * Initialize the specified lookups, either immediately or when the injector is created.
 */
public void initialize(Errors errors){
  injector.lookups=injector;
  new LookupProcessor(errors).process(injector,lookups);
}
