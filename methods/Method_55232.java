/** 
 * Returns the specified property of a given protocol.
 * @param proto              a protocol
 * @param name               the name of a property
 * @param isRequiredProperty a Boolean value that indicates whether {@code name} is a required property
 * @param isInstanceProperty a Boolean value that indicates whether {@code name} is a instance property
 * @return the property specified by {@code name},  {@code isRequiredProperty}, and  {@code isInstanceProperty} for {@code proto}, or  {@code NULL} if none of{@code proto}'s properties meets the specification
 */
@NativeType("objc_property_t") public static long protocol_getProperty(@NativeType("Protocol *") long proto,@NativeType("char const *") ByteBuffer name,@NativeType("BOOL") boolean isRequiredProperty,@NativeType("BOOL") boolean isInstanceProperty){
  if (CHECKS) {
    checkNT1(name);
  }
  return nprotocol_getProperty(proto,memAddress(name),isRequiredProperty,isInstanceProperty);
}
