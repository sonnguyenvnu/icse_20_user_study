/** 
 * Create an HTML (with SVG) page representing the slide.
 * @param presentationMLPackage
 * @param slide
 * @param settings
 * @return
 * @throws Exception
 */
public static String svg(PresentationMLPackage presentationMLPackage,SlidePart slide,SvgSettings settings) throws Exception {
  ResolvedLayout rl=((SlidePart)slide).getResolvedLayout();
  return SvgExporter.svg(presentationMLPackage,rl,settings);
}
