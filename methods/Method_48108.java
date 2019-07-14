/** 
 * Constructs a Geoshape from a spatial4j  {@link Shape}.
 * @param shape
 * @return
 */
public static Geoshape geoshape(Shape shape){
  return new Geoshape(shape);
}
