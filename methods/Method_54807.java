/** 
 * Sets the specified value to the  {@code m_numVisualShapes} field of the specified {@code struct}. 
 */
public static void nm_numVisualShapes(long struct,int value){
  UNSAFE.putInt(null,struct + B3VisualShapeInformation.M_NUMVISUALSHAPES,value);
}
