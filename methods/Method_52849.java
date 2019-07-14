public Class<?> findClass(String name) throws ClassNotFoundException {
  if (resolvers.isEmpty()) {
    buildResolvers();
  }
  for (  Resolver resolver : resolvers) {
    try {
      return resolver.resolve(name);
    }
 catch (    ClassNotFoundException ignored) {
    }
  }
  throw new ClassNotFoundException("Type " + name + " not found");
}
