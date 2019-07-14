/** 
 * Adds a failure with the given  {@code reason}to the table if  {@code matcher} does not match {@code value}. Execution continues, but the test will fail at the end if the match fails.
 * @deprecated use {@code org.hamcrest.junit.ErrorCollector.checkThat()}
 */
@Deprecated public <T>void checkThat(final String reason,final T value,final Matcher<T> matcher){
  checkSucceeds(new Callable<Object>(){
    public Object call() throws Exception {
      assertThat(reason,value,matcher);
      return value;
    }
  }
);
}
