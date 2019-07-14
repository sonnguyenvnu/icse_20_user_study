/** 
 * Inflates a new hierarchy from XML.
 */
public static GenericDraweeHierarchy inflateHierarchy(Context context,@Nullable AttributeSet attrs){
  return inflateBuilder(context,attrs).build();
}
