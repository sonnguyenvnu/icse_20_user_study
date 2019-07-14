public static void tryClose(final AutoCloseable... items){
  if (items == null) {
    return;
  }
  for (  AutoCloseable c : items) {
    tryClose(c);
  }
}
