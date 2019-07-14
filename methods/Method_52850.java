private void buildResolvers(){
  resolvers.add(new PrimitiveTypeResolver());
  resolvers.add(new VoidResolver());
  resolvers.add(new ExplicitImportResolver(imports));
  resolvers.add(new CurrentPackageResolver(pkg));
  resolvers.add(new ImplicitImportResolver());
  resolvers.add(new ImportOnDemandResolver(imports));
  resolvers.add(new FullyQualifiedNameResolver());
}
