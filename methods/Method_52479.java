private void buildResolvers(){
  resolvers.add(new PrimitiveTypeResolver());
  resolvers.add(new VoidResolver());
  resolvers.add(new ExplicitImportResolver(pmdClassLoader,imports));
  resolvers.add(new CurrentPackageResolver(pmdClassLoader,pkg));
  resolvers.add(new ImplicitImportResolver(pmdClassLoader));
  resolvers.add(new ImportOnDemandResolver(pmdClassLoader,imports));
  resolvers.add(new FullyQualifiedNameResolver(pmdClassLoader));
}
