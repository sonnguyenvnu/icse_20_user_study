/** 
 * Creates a new static  {@link Services} instance and starts the services.
 * @throws IllegalStateException If the services have already been created.
 */
public static void create(){
  if (instance != null) {
    throw new IllegalStateException("Services have already been created");
  }
  instance=new Services();
}
