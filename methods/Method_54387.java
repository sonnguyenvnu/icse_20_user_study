/** 
 * Sets the specified value to the  {@code m_numCollisionShapes} field of the specified {@code struct}. 
 */
public static void nm_numCollisionShapes(long struct,int value){
  UNSAFE.putInt(null,struct + B3CollisionShapeInformation.M_NUMCOLLISIONSHAPES,value);
}
