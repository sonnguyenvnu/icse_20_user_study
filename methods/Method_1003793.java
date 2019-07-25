/** 
 * Creates a  {@link ratpack.handling.Context#render renderable object} to render the given object as JSON.<p> The given object will be converted to JSON using a  {@link com.google.gson.Gson} instance obtained from the context registry.<p> See the <a href="#rendering">rendering</a> section for usage examples.
 * @param object the object to render as JSON
 * @return a renderable wrapper for the given object
 */
public static GsonRender json(Object object){
  return new DefaultGsonRender(object,null);
}
