/** 
 * Check whether the delegate methods contain an <code>@OnError</code> declaration. 
 */
public static boolean hasOnErrorDelegateMethod(ImmutableList<SpecMethodModel<DelegateMethod,Void>> delegateMethods){
  return delegateMethods.stream().flatMap(m -> m.annotations.stream()).anyMatch(ann -> ann.annotationType().equals(OnError.class));
}
