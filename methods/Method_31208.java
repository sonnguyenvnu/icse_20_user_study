/** 
 * This is your starting point. This creates a configuration which can be customized to your needs before being loaded into a new Flyway instance using the load() method. <p>In its simplest form, this is how you configure Flyway with all defaults to get started:</p> <pre>Flyway flyway = Flyway.configure().dataSource(url, user, password).load();</pre> <p>After that you have a fully-configured Flyway instance at your disposal which can be used to invoke Flyway functionality such as migrate() or clean().</p>
 * @return A new configuration from which Flyway can be loaded.
 */
public static FluentConfiguration configure(){
  return new FluentConfiguration();
}
