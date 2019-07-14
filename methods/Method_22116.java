private static boolean isIgnoredException(final Throwable cause){
  return cause instanceof ConnectionLossException || cause instanceof NoNodeException || cause instanceof NodeExistsException;
}
