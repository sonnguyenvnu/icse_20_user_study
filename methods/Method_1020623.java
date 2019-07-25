/** 
 * Convert a  {@code Connectable<I, O>} to a {@code Connectable<J, O>} by applying the suppliedfunction from J to I for each J received, before passing it on to a  {@code Connection<I>}received from the underlying  {@code Connectable<I, O>}. This makes  {@link Connectable} a <ahref="https://hackage.haskell.org/package/contravariant-1.4.1/docs/Data-Functor-Contravariant.html">contravariant functor</a> in functional programming terms. <p>The returned  {@link Connectable} doesn't enforce a connection limit, but of course theconnection limit of the wrapped  {@link Connectable} applies.<p>This is useful for instance if you want your UI to use a subset or a transformed version of the full model used in the  {@code MobiusLoop}. As a simplified example, suppose that your model consists of a  {@code Long} timestamp that you want to format to a {@code String} beforerendering it in the UI. Your UI could then implement  {@code Connectable<String, Event>}, and you could create a  {@code NullValuedFunction<Long, String>} that does the formatting. The{@link com.spotify.mobius.MobiusLoop} would be outputting {@code Long} models that you need toconvert to Strings before they can be accepted by the UI. <pre> public class Formatter { public static String format(Long timestamp) { ... } } public class MyUi implements Connectable<String, Event> { // other things in the UI implementation {@literal @}Override public Connection<String> connect(Consumer<Event> output) { return new Connection<String>() { {@literal @}Override public void accept(String value) { // bind the value to the right UI element } {@literal @}Override public void dispose() { // dispose of any resources, if needed } } } } // Then, to connect the UI to a MobiusLoop.Controller with a Long model: MobiusLoop.Controller<Long, Event> controller = ... ; MyUi myUi = ... ; controller.connect(Connectables.contramap(Formatter::format, myUi)); </pre>
 * @param mapper the mapping function to apply
 * @param connectable the underlying connectable
 * @param < I > the type the underlying connectable accepts
 * @param < J > the type the resulting connectable accepts
 * @param < O > the output type; usually the event type
 */
@Nonnull public static <I,J,O>Connectable<J,O> contramap(final Function<J,I> mapper,final Connectable<I,O> connectable){
  return SimpleConnectable.withConnectionFactory(new Function<Consumer<O>,Connection<J>>(){
    @Nonnull @Override public Connection<J> apply(    Consumer<O> output){
      return ContramapConnection.create(mapper,connectable,output);
    }
  }
);
}
