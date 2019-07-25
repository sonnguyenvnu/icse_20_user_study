/** 
 * Registers the configuration action. <p> This method is called by  {@link ratpack.guice.BindingsSpec#module(Class,Action)}.
 * @param configurer the configuration action.
 */
public void configure(Action<? super T> configurer){
  this.configurer=configurer;
}
