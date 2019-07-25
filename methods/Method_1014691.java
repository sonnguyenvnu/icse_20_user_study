public void register(@NonNull Object observer){
  ObjectHelper.requireNonNull(observer,"Observer to register must not be null.");
  Class<?> observerClass=observer.getClass();
  if (OBSERVERS.putIfAbsent(observerClass,new CompositeDisposable()) != null) {
    return;
  }
  CompositeDisposable composite=OBSERVERS.get(observerClass);
  Set<Class<?>> events=new HashSet<>();
  for (  Method method : observerClass.getDeclaredMethods()) {
    if (method.isBridge() || method.isSynthetic())     continue;
    if (!method.isAnnotationPresent(Subscribe.class))     continue;
    int mod=method.getModifiers();
    if (Modifier.isStatic(mod) || !Modifier.isPublic(mod))     throw new IllegalArgumentException("Method " + method.getName() + " has @Subscribe annotation must be public, non-static");
    Class<?>[] params=method.getParameterTypes();
    if (params.length != 1)     throw new IllegalArgumentException("Method " + method.getName() + " has @Subscribe annotation must require a single argument");
    Class<?> eventClass=params[0];
    if (eventClass.isInterface())     throw new IllegalArgumentException("Event class must be on a concrete class type.");
    if (!events.add(eventClass))     throw new IllegalArgumentException("Subscriber for " + eventClass.getSimpleName() + " has already been registered.");
    composite.add(bus.ofType(eventClass).observeOn(AndroidSchedulers.mainThread()).subscribe(new AnnotatedSubscriber<>(observer,method)));
  }
}
