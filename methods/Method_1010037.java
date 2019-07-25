/** 
 * @deprecated operand is not null actually, use #invoke0 below instead
 */
@Deprecated @ToRemove(version=2018.1) public static Object invoke(@Nullable SAbstractConcept operand,@NotNull SMethodId methodId,Object... parameters){
  return invoke0(operand,operand,methodId,parameters);
}
