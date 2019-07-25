/** 
 * {@inheritDoc}<p> Default the targetType should be assignable to the returnType and the sourceType to the parameter, excluding generic type variables. When the implementor sees a need for this, this method can be overridden.
 */
@Override public boolean matches(List<Type> sourceTypes,Type targetType){
  throw new IllegalStateException("Irrelevant.");
}
