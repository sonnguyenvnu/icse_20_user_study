@Override public <A>A adapt(Class<A> type){
  try {
    ServiceReference<?>[] references=context.getAllServiceReferences(type.getName(),"");
    if ((null != references) && (0 != references.length)) {
      if (1 != references.length) {
        throw new IllegalArgumentException("Multiple services found for " + type.getName());
      }
      Object obj=context.getService(references[0]);
      try {
        return type.cast(obj);
      }
 catch (      ClassCastException e) {
        throw new IllegalArgumentException("Received unexpected class for reference filter " + type.getName(),e);
      }
    }
    return null;
  }
 catch (  InvalidSyntaxException e) {
    throw new IllegalArgumentException("Unexpected syntax exception",e);
  }
}
