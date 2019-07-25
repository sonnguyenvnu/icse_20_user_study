/** 
 * Registers an  {@link RxJavaObservableExecutionHook} with RxJava that provides a default error handling strategy of forwarding exceptions to the execution error handler.<p> This method is idempotent. It only needs to be called once per JVM, regardless of how many Ratpack applications are running within the JVM. <p> For a Java application, a convenient place to call this is in the handler factory implementation. <pre class="java"> {@code import ratpack.error.ServerErrorHandler; import ratpack.rx.RxRatpack; import ratpack.test.embed.EmbeddedApp; import rx.Observable; import static org.junit.Assert.assertEquals;}public class Example  public static void main(String... args) throws Exception { RxRatpack.initialize(); // must be called once for the life of the JVM EmbeddedApp.fromHandlers(chain -> chain .register(s -> s .add(ServerErrorHandler.class, (ctx, throwable) -> ctx.render("caught by error handler: " + throwable.getMessage()) ) ) .get(ctx -> Observable.<String>error(new Exception("!")).subscribe(ctx::render)) ).test(httpClient -> assertEquals("caught by error handler: !", httpClient.getText()) ); } } }</pre>
 */
@SuppressWarnings("deprecation") public static void initialize(){
  RxJavaPlugins plugins=RxJavaPlugins.getInstance();
  ExecutionHook ourHook=new ExecutionHook();
  try {
    plugins.registerObservableExecutionHook(ourHook);
  }
 catch (  IllegalStateException e) {
    RxJavaObservableExecutionHook existingHook=plugins.getObservableExecutionHook();
    if (!(existingHook instanceof ExecutionHook)) {
      throw new IllegalStateException("Cannot install RxJava integration because another execution hook (" + existingHook.getClass() + ") is already installed");
    }
  }
  ErrorHandler ourErrorHandler=new ErrorHandler();
  try {
    plugins.registerErrorHandler(ourErrorHandler);
  }
 catch (  IllegalStateException e) {
    RxJavaErrorHandler existingErrorHandler=plugins.getErrorHandler();
    if (!(existingErrorHandler instanceof ErrorHandler)) {
      throw new IllegalStateException("Cannot install RxJava integration because another error handler (" + existingErrorHandler.getClass() + ") is already installed");
    }
  }
  try {
    plugins.registerSchedulersHook(new DefaultSchedulers());
  }
 catch (  IllegalStateException e) {
    rx.plugins.RxJavaSchedulersHook existingSchedulers=plugins.getSchedulersHook();
    if (!(existingSchedulers instanceof DefaultSchedulers)) {
      throw new IllegalStateException("Cannot install RxJava integration because another set of default schedulers (" + existingSchedulers.getClass() + ") is already installed");
    }
  }
}
