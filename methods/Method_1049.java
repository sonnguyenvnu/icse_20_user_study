private static void closeSilently(@Nullable Closeable closeable){
  if (closeable == null) {
    return;
  }
  try {
    closeable.close();
  }
 catch (  IOException ignored) {
  }
}
