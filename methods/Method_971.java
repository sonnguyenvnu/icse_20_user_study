private static @Nullable AnimatedImageDecoder loadIfPresent(final String className){
  try {
    Class<?> clazz=Class.forName(className);
    return (AnimatedImageDecoder)clazz.newInstance();
  }
 catch (  Throwable e) {
    return null;
  }
}
