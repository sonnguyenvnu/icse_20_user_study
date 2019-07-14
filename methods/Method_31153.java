/** 
 * The manually added Java-based migrations. These are not Java-based migrations discovered through classpath scanning and instantiated by Flyway. Instead these are manually added instances of JavaMigration. This is particularly useful when working with a dependency injection container, where you may want the DI container to instantiate the class and wire up its dependencies for you.
 * @param javaMigrations The manually added Java-based migrations. An empty array if none. (default: none)
 */
public void setJavaMigrations(JavaMigration... javaMigrations){
  if (javaMigrations == null) {
    throw new FlywayException("javaMigrations cannot be null");
  }
  this.javaMigrations=javaMigrations;
}
