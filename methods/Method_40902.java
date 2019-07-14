/** 
 * Returns a SettableSupplier that supplies the set value once then uses the  {@code supplier} for subsequent calls. 
 */
static <T>SettableSupplier<CompletableFuture<T>> settableSupplierOf(Supplier<CompletableFuture<T>> supplier){
  return new SettableSupplier<CompletableFuture<T>>(){
    @Override public CompletableFuture<T> get(){
      if (!called && value != null) {
        called=true;
        return value;
      }
 else       return supplier.get();
    }
    @Override public void set(    CompletableFuture<T> value){
      called=false;
      this.value=value;
    }
  }
;
}
