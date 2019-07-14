/** 
 * Sets the specified value to the  {@code mNumVertices} field of the specified {@code struct}. 
 */
public static void nmNumVertices(long struct,int value){
  UNSAFE.putInt(null,struct + AIMesh.MNUMVERTICES,value);
}
