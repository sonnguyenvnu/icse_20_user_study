/** 
 * Adds a  {@link Consumer} that performs additional configuration operations againstthe Tomcat  {@link StandardServer} created by a {@link TomcatService}.
 */
public TomcatServiceBuilder configurator(Consumer<? super StandardServer> configurator){
  configurators.add(requireNonNull(configurator,"configurator"));
  return this;
}
