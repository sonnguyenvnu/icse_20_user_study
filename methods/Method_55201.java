/** 
 * Sets the Ivar layout for a given class.
 * @param cls    the class to modify
 * @param layout the layout of the Ivars for {@code cls}
 */
public static void class_setIvarLayout(@NativeType("Class") long cls,@NativeType("uint8_t const *") ByteBuffer layout){
  if (CHECKS) {
    checkNT1(layout);
  }
  nclass_setIvarLayout(cls,memAddress(layout));
}
