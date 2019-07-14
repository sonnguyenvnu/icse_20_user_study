public static SerializerContext getInstance(){
  if (context == null) {
synchronized (SerializerContext.class) {
      if (context == null) {
        context=new SerializerContext();
      }
    }
  }
  return context;
}
