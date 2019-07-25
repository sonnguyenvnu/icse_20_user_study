/** 
 * Create an HTML (with SVG) page representing the slide.
 * @param presentationMLPackage
 * @param slide
 * @return
 * @throws Exception
 */
public static String svg(PresentationMLPackage presentationMLPackage,SlidePart slide) throws Exception {
  return svg(presentationMLPackage,slide,null);
}
