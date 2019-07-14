private static void closeSafely(Closeable closeable){
  if (closeable != null) {
    try {
      closeable.close();
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
}
