/** 
 * Adds a  {@link Consumer} that performs additional configuration operations againstthe Jetty  {@link Server} created by a {@link JettyService}.
 */
public JettyServiceBuilder configurator(Consumer<? super Server> configurator){
  configurators.add(requireNonNull(configurator,"configurator"));
  return this;
}
