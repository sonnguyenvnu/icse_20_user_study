/** 
 * Inlines the appropriate chain based on the given  {@code test}. <p> This is literally just sugar for wrapping the given action in an  {@code if/else} statement.It can be useful when conditionally adding handlers based on state available when building the chain. <pre class="java"> {@code import ratpack.test.embed.EmbeddedApp; import static org.junit.Assert.assertEquals;}public class Example  public static void main(String... args) throws Exception { EmbeddedApp.of(a -> a .registryOf(r -> r.add(1)) .handlers(c -> c .when(c.getRegistry().get(Integer.class) == 0, i -> i.get(ctx -> ctx.render("ok")), i -> i.get(ctx -> ctx.render("ko")) ) ) ).test(httpClient -> assertEquals(httpClient.getText(), "ko") ); EmbeddedApp.of(a -> a .registryOf(r -> r.add(0)) .handlers(c -> c .when(c.getRegistry().get(Integer.class) == 0, i -> i.get(ctx -> ctx.render("ok")), i -> i.get(ctx -> ctx.render("ko")) ) ) ).test(httpClient -> assertEquals(httpClient.getText(), "ok") ); } } }</pre>
 * @param test predicate to decide which action include
 * @param onTrue the chain action to include when the predicate is true
 * @param onFalse the chain action to include when the predicate is false
 * @return this
 * @throws Exception any thrown by {@code action}
 * @since 1.5
 */
default Chain when(boolean test,Action<? super Chain> onTrue,Action<? super Chain> onFalse) throws Exception {
  if (test) {
    onTrue.execute(this);
  }
 else {
    onFalse.execute(this);
  }
  return this;
}
