private static AnnotatedType zilch(){
  try {
    return ParameterTypeContext.class.getDeclaredField("zilch").getAnnotatedType();
  }
 catch (  NoSuchFieldException e) {
    throw new AssertionError(e);
  }
}
