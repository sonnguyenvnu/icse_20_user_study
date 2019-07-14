/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  check(memGetAddress(struct + B3UserDataValue.M_DATA1));
}
