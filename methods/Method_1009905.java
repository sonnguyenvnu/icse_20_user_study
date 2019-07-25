/** 
 * Validates these options according to the allowed criteria and checks for inconsistencies in flag values. <p> Note that there is not requirement for options used internally in custom message parsers to be validated, but any format options passed through the  {@code ParameterVisitor} interface mustbe valid with respect to the associated  {@link FormatChar} instance.
 * @param allowedFlags a bit mask specifying a subset of the printf flags that are allowed forthese options.
 * @param allowPrecision true if these options are allowed to have a precision value specified.
 * @return true if these options are valid given the specified constraints.
 */
public boolean validate(int allowedFlags,boolean allowPrecision){
  if (isDefault()) {
    return true;
  }
  if ((flags & ~allowedFlags) != 0) {
    return false;
  }
  if (!allowPrecision && precision != UNSET) {
    return false;
  }
  return checkFlagConsistency(flags,getWidth() != UNSET);
}
