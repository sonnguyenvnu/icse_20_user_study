/** 
 * Like  {@link #create(long) create}, but returns  {@code null} if {@code address} is {@code NULL}. 
 */
@Nullable public static B3VisualShapeInformation createSafe(long address){
  return address == NULL ? null : wrap(B3VisualShapeInformation.class,address);
}
