/** 
 * Make engine as a consumer to accept parsed data from user
 * @return A consumer will call {@link BaseTangramEngine#setData(List)}
 * @since 3.0.0
 */
public Consumer<List<C>> asParsedDataConsumer(){
  return new Consumer<List<C>>(){
    @Override public void accept(    List<C> cs) throws Exception {
      setData(cs);
    }
  }
;
}
