private void refresh(ClassLoader classLoader,int timeout){
  if (!registeredDispatchers.isEmpty()) {
    try {
      Class<?> cmdClass=Class.forName(RefreshDispatchersCommand.class.getName(),true,appClassLoader);
      Command cmd=(Command)cmdClass.newInstance();
      ReflectionHelper.invoke(cmd,cmdClass,"setupCmd",new Class[]{java.lang.ClassLoader.class,java.util.Set.class},classLoader,registeredDispatchers);
      scheduler.scheduleCommand(cmd,timeout);
    }
 catch (    Exception e) {
      LOGGER.error("refresh() exception {}.",e.getMessage());
    }
  }
}
