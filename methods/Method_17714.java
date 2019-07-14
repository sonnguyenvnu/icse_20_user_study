/** 
 * @return a RuntimeValue that resolves to an offset relative to the current value of some mountcontent property.
 */
public static DimensionValue offsetPx(float value){
  return new DimensionValue(Type.OFFSET,value);
}
