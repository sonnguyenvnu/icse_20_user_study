static ControllerHelper getHelperForController(EpoxyController controller){
  Constructor<?> constructor=findConstructorForClass(controller.getClass());
  if (constructor == null) {
    return NO_OP_CONTROLLER_HELPER;
  }
  try {
    return (ControllerHelper)constructor.newInstance(controller);
  }
 catch (  IllegalAccessException e) {
    throw new RuntimeException("Unable to invoke " + constructor,e);
  }
catch (  InstantiationException e) {
    throw new RuntimeException("Unable to invoke " + constructor,e);
  }
catch (  InvocationTargetException e) {
    Throwable cause=e.getCause();
    if (cause instanceof RuntimeException) {
      throw (RuntimeException)cause;
    }
    if (cause instanceof Error) {
      throw (Error)cause;
    }
    throw new RuntimeException("Unable to get Epoxy helper class.",cause);
  }
}
