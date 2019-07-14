@Override public void start(){
  if (providerFactory == null) {
    providerFactory=new ResteasyProviderFactory();
  }
  providerFactory.setRegisterBuiltins(registerBuiltin);
  if (deploymentSensitiveFactoryEnabled) {
    if (!(providerFactory instanceof ThreadLocalResteasyProviderFactory)) {
      if (ResteasyProviderFactory.peekInstance() == null || !(ResteasyProviderFactory.peekInstance() instanceof ThreadLocalResteasyProviderFactory)) {
        threadLocalProviderFactory=new ThreadLocalResteasyProviderFactory(providerFactory);
        ResteasyProviderFactory.setInstance(threadLocalProviderFactory);
      }
    }
  }
 else {
    ResteasyProviderFactory.setInstance(providerFactory);
  }
  if (asyncJobServiceEnabled) {
    AsynchronousDispatcher asyncDispatcher=new AsynchronousDispatcher(providerFactory);
    asyncDispatcher.setMaxCacheSize(asyncJobServiceMaxJobResults);
    asyncDispatcher.setMaxWaitMilliSeconds(asyncJobServiceMaxWait);
    asyncDispatcher.setThreadPoolSize(asyncJobServiceThreadPoolSize);
    asyncDispatcher.setBasePath(asyncJobServiceBasePath);
    asyncDispatcher.getUnwrappedExceptions().addAll(unwrappedExceptions);
    dispatcher=asyncDispatcher;
    asyncDispatcher.start();
  }
 else {
    SynchronousDispatcher dis=new SofaSynchronousDispatcher(providerFactory);
    dis.getUnwrappedExceptions().addAll(unwrappedExceptions);
    dispatcher=dis;
  }
  registry=dispatcher.getRegistry();
  if (widerRequestMatching) {
    ((ResourceMethodRegistry)registry).setWiderMatching(widerRequestMatching);
  }
  dispatcher.getDefaultContextObjects().putAll(defaultContextObjects);
  dispatcher.getDefaultContextObjects().put(Configurable.class,providerFactory);
  dispatcher.getDefaultContextObjects().put(Providers.class,providerFactory);
  dispatcher.getDefaultContextObjects().put(Registry.class,registry);
  dispatcher.getDefaultContextObjects().put(Dispatcher.class,dispatcher);
  dispatcher.getDefaultContextObjects().put(InternalDispatcher.class,InternalDispatcher.getInstance());
  Map contextDataMap=ResteasyProviderFactory.getContextDataMap();
  contextDataMap.putAll(dispatcher.getDefaultContextObjects());
  try {
    if (injectorFactoryClass != null) {
      InjectorFactory injectorFactory;
      try {
        Class<?> clazz=Thread.currentThread().getContextClassLoader().loadClass(injectorFactoryClass);
        injectorFactory=(InjectorFactory)clazz.newInstance();
      }
 catch (      ClassNotFoundException cnfe) {
        throw new RuntimeException("Unable to find InjectorFactory implementation.",cnfe);
      }
catch (      Exception e) {
        throw new RuntimeException("Unable to instantiate InjectorFactory implementation.",e);
      }
      providerFactory.setInjectorFactory(injectorFactory);
    }
    if (constructedDefaultContextObjects != null && constructedDefaultContextObjects.size() > 0) {
      for (      Map.Entry<String,String> entry : constructedDefaultContextObjects.entrySet()) {
        Class<?> key=null;
        try {
          key=Thread.currentThread().getContextClassLoader().loadClass(entry.getKey());
        }
 catch (        ClassNotFoundException e) {
          throw new RuntimeException("Unable to instantiate context object " + entry.getKey(),e);
        }
        Object obj=createFromInjectorFactory(entry.getValue(),providerFactory);
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("Creating context object <" + entry.getKey() + " : " + entry.getValue() + ">");
        }
        defaultContextObjects.put(key,obj);
        dispatcher.getDefaultContextObjects().put(key,obj);
        contextDataMap.put(key,obj);
      }
    }
    if (interceptorPrecedences != null) {
      for (      String precedence : interceptorPrecedences) {
        providerFactory.appendInterceptorPrecedence(precedence.trim());
      }
    }
    if (interceptorBeforePrecedences != null) {
      for (      Map.Entry<String,String> ext : interceptorBeforePrecedences.entrySet()) {
        providerFactory.insertInterceptorPrecedenceBefore(ext.getKey().trim(),ext.getValue().trim());
      }
    }
    if (interceptorAfterPrecedences != null) {
      for (      Map.Entry<String,String> ext : interceptorAfterPrecedences.entrySet()) {
        providerFactory.insertInterceptorPrecedenceAfter(ext.getKey().trim(),ext.getValue().trim());
      }
    }
    if (securityEnabled) {
      providerFactory.register(RoleBasedSecurityFeature.class);
    }
    if (registerBuiltin) {
      providerFactory.setRegisterBuiltins(true);
      RegisterBuiltin.register(providerFactory);
      providerFactory.registerProviderInstance(new ServerFormUrlEncodedProvider(useContainerFormParams),null,null,true);
    }
 else {
      providerFactory.setRegisterBuiltins(false);
    }
    if (applicationClass != null) {
      application=createApplication(applicationClass,dispatcher,providerFactory);
    }
    registration();
    if (paramMapping != null) {
      providerFactory.getContainerRequestFilterRegistry().registerSingleton(new AcceptParameterHttpPreprocessor(paramMapping));
    }
    AcceptHeaderByFileSuffixFilter suffixNegotiationFilter=null;
    if (mediaTypeMappings != null) {
      Map<String,MediaType> extMap=new HashMap<String,MediaType>();
      for (      Map.Entry<String,String> ext : mediaTypeMappings.entrySet()) {
        String value=ext.getValue();
        extMap.put(ext.getKey().trim(),MediaType.valueOf(value.trim()));
      }
      if (suffixNegotiationFilter == null) {
        suffixNegotiationFilter=new AcceptHeaderByFileSuffixFilter();
        providerFactory.getContainerRequestFilterRegistry().registerSingleton(suffixNegotiationFilter);
      }
      suffixNegotiationFilter.setMediaTypeMappings(extMap);
    }
    if (languageExtensions != null) {
      if (suffixNegotiationFilter == null) {
        suffixNegotiationFilter=new AcceptHeaderByFileSuffixFilter();
        providerFactory.getContainerRequestFilterRegistry().registerSingleton(suffixNegotiationFilter);
      }
      suffixNegotiationFilter.setLanguageMappings(languageExtensions);
    }
  }
  finally {
    ResteasyProviderFactory.removeContextDataLevel();
  }
}
