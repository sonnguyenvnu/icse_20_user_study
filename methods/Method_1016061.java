/** 
 * This is a shortcut to configuring the LifecycleInjectorBuilder using annotations. Using bootstrap a main application class can simply be annotated with  custom annotations that are mapped to  {@link BootstrapModule}s. Each annotations can then map to a subsystem or feature that is enabled on  the main application class.   {@link BootstrapModule}s are installed in the order in which  they are defined.
 * @see {@link Bootstrap}
 * @param main Main application bootstrap class
 * @param externalBindings Bindings that are provided externally by the caller to bootstrap.  Thesebindings are injectable into the BootstrapModule instances
 * @param externalBootstrapModules Optional modules that are processed after all the main class bootstrap modules
 * @return The created injector
 */
@Beta public static Injector bootstrap(final Class<?> main,final Module externalBindings,final BootstrapModule... externalBootstrapModules){
  final LifecycleInjectorBuilder builder=LifecycleInjector.builder();
  Injector injector=Guice.createInjector(new AbstractModule(){
    @SuppressWarnings({"unchecked","rawtypes"}) @Override protected void configure(){
      if (externalBindings != null)       install(externalBindings);
      Multibinder<BootstrapModule> bootstrapModules=Multibinder.newSetBinder(binder(),BootstrapModule.class);
      Multibinder<LifecycleInjectorBuilderSuite> suites=Multibinder.newSetBinder(binder(),LifecycleInjectorBuilderSuite.class);
      if (externalBootstrapModules != null) {
        for (        final BootstrapModule bootstrapModule : externalBootstrapModules) {
          bootstrapModules.addBinding().toProvider(new MemberInjectingInstanceProvider<BootstrapModule>(bootstrapModule));
        }
      }
      for (      final Annotation annot : main.getDeclaredAnnotations()) {
        final Class<? extends Annotation> type=annot.annotationType();
        LOG.info("Found bootstrap annotation {}",type.getName());
        Bootstrap bootstrap=type.getAnnotation(Bootstrap.class);
        if (bootstrap != null) {
          boolean added=false;
          if (!bootstrap.value().equals(Bootstrap.NullLifecycleInjectorBuilderSuite.class)) {
            LOG.info("Adding Suite {}",bootstrap.bootstrap());
            suites.addBinding().to(bootstrap.value()).asEagerSingleton();
            added=true;
          }
          if (!bootstrap.bootstrap().equals(Bootstrap.NullBootstrapModule.class)) {
            Preconditions.checkState(added == false,"%s already added as a LifecycleInjectorBuilderSuite",bootstrap.annotationType().getName());
            added=true;
            LOG.info("Adding BootstrapModule {}",bootstrap.bootstrap());
            bootstrapModules.addBinding().to(bootstrap.bootstrap()).asEagerSingleton();
            builder.withAdditionalBootstrapModules(forAnnotation(annot));
          }
          if (!bootstrap.module().equals(Bootstrap.NullModule.class)) {
            Preconditions.checkState(added == false,"%s already added as a BootstrapModule",bootstrap.annotationType().getName());
            added=true;
            LOG.info("Adding Module {}",bootstrap.bootstrap());
            builder.withAdditionalModuleClasses(bootstrap.module());
            builder.withAdditionalBootstrapModules(forAnnotation(annot));
          }
          bind(Key.get(type)).toProvider(new Provider(){
            @Override public Object get(){
              return annot;
            }
          }
).in(Scopes.SINGLETON);
        }
      }
    }
  }
);
  Set<LifecycleInjectorBuilderSuite> suites=injector.getInstance(Key.get(new TypeLiteral<Set<LifecycleInjectorBuilderSuite>>(){
  }
));
  for (  LifecycleInjectorBuilderSuite suite : suites) {
    suite.configure(builder);
  }
  builder.withAdditionalBootstrapModules(injector.getInstance(Key.get(new TypeLiteral<Set<BootstrapModule>>(){
  }
)));
  if (Module.class.isAssignableFrom(main)) {
    try {
      builder.withAdditionalModuleClasses(main);
    }
 catch (    Exception e) {
      throw new ProvisionException(String.format("Failed to create module for main class '%s'",main.getName()),e);
    }
  }
  return builder.build().createInjector();
}
