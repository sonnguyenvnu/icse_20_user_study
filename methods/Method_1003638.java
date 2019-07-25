/** 
 * Adds a handler that serves files from the file system. <p> The given action configures how and what files will be served. The handler binds to a  {@link FileHandlerSpec#path(String) request path}and a  {@link FileHandlerSpec#dir(String) directory} within the current filesystem binding.The portion of the request path <i>past</i> the path binding identifies the target file within the directory. <pre class="java"> {@code import ratpack.test.embed.EphemeralBaseDir; import ratpack.test.embed.EmbeddedApp; import static org.junit.Assert.assertEquals;}public class Example  public static void main(String... args) throws Exception { EphemeralBaseDir.tmpDir().use(baseDir -> { baseDir.write("public/some.text", "foo"); baseDir.write("public/index.html", "bar"); EmbeddedApp.of(s -> s .serverConfig(c -> c.baseDir(baseDir.getRoot())) .handlers(c -> c .files(f -> f.dir("public").indexFiles("index.html")) ) ).test(httpClient -> { assertEquals("foo", httpClient.getText("some.text")); assertEquals("bar", httpClient.getText()); assertEquals(404, httpClient.get("no-file-here").getStatusCode()); }); }); } } }</pre>
 * @param config the file handler configuration
 * @return {@code this}
 * @throws Exception any thrown by {@code config}
 * @see Handlers#files(ServerConfig,Action)
 * @see FileHandlerSpec
 */
default Chain files(Action<? super FileHandlerSpec> config) throws Exception {
  return all(Handlers.files(getServerConfig(),config));
}
