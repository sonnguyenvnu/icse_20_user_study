@SneakyThrows default void tryPut(String unique,Supplier<? extends Exception> supplier){
  if (!put(unique)) {
    throw supplier.get();
  }
}
