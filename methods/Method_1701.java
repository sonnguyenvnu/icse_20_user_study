/** 
 * Creates a new request builder instance for a local resource image. <p>Only image resources can be used with the image pipeline (PNG, JPG, GIF). Other resource types such as Strings or XML Drawables make no sense in the context of the image pipeline and so cannot be supported. Attempts to do so will throw an {@link java.lang.IllegalArgumentException} when the pipeline tries to decode the resource.<p>One potentially confusing case is drawable declared in XML (e.g. ShapeDrawable). This is not an image. If you want to display an XML drawable as the main image, then set it as a placeholder and do not set a URI. <p/>
 * @param resId local image resource id.
 * @return a new request builder instance.
 */
public static ImageRequestBuilder newBuilderWithResourceId(int resId){
  return newBuilderWithSource(UriUtil.getUriForResourceId(resId));
}
