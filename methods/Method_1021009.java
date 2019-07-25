public static void hold(String message,Throwable ex){
  messages.get().add(message);
  throwables.get().add(ex);
}
