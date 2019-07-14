private static Locker openManagedLocker(String classname,String lockerName){
  try {
    Class c=Class.forName(classname);
    Constructor constructor=c.getConstructor();
    Object instance=constructor.newInstance();
    Method method=c.getMethod("openLocker",String.class);
    Object o=method.invoke(instance,lockerName);
    return (Locker)o;
  }
 catch (  ClassNotFoundException e) {
    throw new IllegalArgumentException("Could not find implementation class: " + classname);
  }
catch (  InstantiationException|ClassCastException e) {
    throw new IllegalArgumentException("Could not instantiate implementation: " + classname,e);
  }
catch (  NoSuchMethodException e) {
    throw new IllegalArgumentException("Could not find method when configuring locking for: " + classname,e);
  }
catch (  IllegalAccessException e) {
    throw new IllegalArgumentException("Could not access method when configuring locking for: " + classname,e);
  }
catch (  InvocationTargetException e) {
    throw new IllegalArgumentException("Could not invoke method when configuring locking for: " + classname,e);
  }
}
