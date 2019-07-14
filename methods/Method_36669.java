/** 
 * Make engine as a consumer to accept origin data from user
 * @return A consumer will call {@link BaseTangramEngine#setData(Object)}
 * @since 3.0.0
 */
public Consumer<T> asOriginalDataConsumer(){
  return new Consumer<T>(){
    @Override public void accept(    T t) throws Exception {
      setData(t);
    }
  }
;
}
