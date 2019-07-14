private void destroyEach(Object object){
  if (object instanceof Closeable) {
    try {
      ((Closeable)object).close();
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
}
