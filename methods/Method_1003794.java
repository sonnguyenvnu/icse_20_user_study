/** 
 * Creates a  {@link ratpack.handling.Context#render renderable object} to render the given object as JSON.<p> The given object will be converted to JSON using the given  {@link com.google.gson.Gson}. If it is  {@code null}, a  {@code Gson} instance will be obtained from the context registry.<p> See the <a href="#rendering">rendering</a> section for usage examples.
 * @param object the object to render as JSON
 * @param gson the Gson instance to use to serialize the object to JSON
 * @return a renderable wrapper for the given object
 */
public static GsonRender json(Object object,@Nullable com.google.gson.Gson gson){
  return new DefaultGsonRender(object,gson);
}
