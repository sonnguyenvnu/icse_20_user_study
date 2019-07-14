/** 
 * Returns true if this is a no-arg method that returns a value assignable to  {@code type}
 * @deprecated This is used only by the Theories runner, and does notuse all the generic type info that it ought to. It will be replaced with a forthcoming ParameterSignature#canAcceptResultOf(FrameworkMethod) once Theories moves to junit-contrib.
 */
@Deprecated public boolean producesType(Type type){
  return getParameterTypes().length == 0 && type instanceof Class<?> && ((Class<?>)type).isAssignableFrom(method.getReturnType());
}
