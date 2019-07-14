/** 
 * Sets the specified value at the specified index of the  {@code mNumUVComponents} field. 
 */
public AIMesh mNumUVComponents(int index,@NativeType("unsigned int") int value){
  nmNumUVComponents(address(),index,value);
  return this;
}
