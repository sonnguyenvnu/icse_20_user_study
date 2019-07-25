@Override public void convert(Throwable t) throws CommandException {
  Class<?> throwableClass=t.getClass();
  for (  ExceptionHandler handler : handlers) {
    if (handler.cls.isAssignableFrom(throwableClass)) {
      try {
        handler.method.invoke(this,t);
      }
 catch (      InvocationTargetException e) {
        if (e.getCause() instanceof CommandException) {
          throw (CommandException)e.getCause();
        }
        if (e.getCause() instanceof com.sk89q.minecraft.util.commands.CommandException) {
          throw new CommandException(e.getCause(),ImmutableList.of());
        }
        throw new CommandExecutionException(e,ImmutableList.of());
      }
catch (      IllegalArgumentException|IllegalAccessException e) {
        throw new CommandExecutionException(e,ImmutableList.of());
      }
    }
  }
}
