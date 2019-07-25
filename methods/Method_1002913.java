/** 
 * Returns a  {@link Response} object which will be converted to a text by the{@link CustomResponseConverter}. A  {@link Request} is also automatically converted bythe  {@link CustomRequestConverter}. <p>If you want to specify converters for every methods in a service class, you can specify them above the class definition as follows: <pre> {@code > @RequestConverter(CustomRequestConverter.class) > @ResponseConverter(CustomResponseConverter.class)}> public class MyAnnotatedService  >     ... > } }</pre>
 */
@Post("/custom") @ProducesText @RequestConverter(CustomRequestConverter.class) @ResponseConverter(CustomResponseConverter.class) public Response custom(@RequestObject Request request){
  return new Response(Response.SUCCESS,request.name());
}
