/** 
 * Sets custom MigrationResolvers to be used in addition to the built-in ones for resolving Migrations to apply.
 * @param resolvers The fully qualified class names of the custom MigrationResolvers to be used in addition to the built-in ones for resolving Migrations to apply. (default: empty list)
 */
public FluentConfiguration resolvers(String... resolvers){
  config.setResolversAsClassNames(resolvers);
  return this;
}
