/** 
 * A Parameter that is not a parameter to anything.
 * @param parameter The original {@link Parameter} that may or may not be parameterized
 * @return an invalid parameter
 */
@NotNull public Parameter not(final @NotNull Parameter parameter){
  Parameter not;
  if (parameter.defaultValue == null && parameter.parameterized == null) {
    not=parameter;
  }
 else {
    not=new Parameter(parameter.entrance);
  }
  return not;
}
