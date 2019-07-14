public static Exception wrapToException(final Throwable throwable){
  if (throwable instanceof Exception) {
    return (Exception)throwable;
  }
  return new RuntimeException(throwable);
}
