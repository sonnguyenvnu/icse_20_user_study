/** 
 * Propagates  {@code throwable} exactly as-is, if and only if it is aninstance of  {@link RuntimeException},  {@link Error},  {@code declaredType1}, or  {@code declaredType2}.  In the unlikely case that you have three or more declared checked exception types, you can handle them all by invoking these methods repeatedly. See usage example in  {@link #propagateIfPossible(Throwable,Class)}.
 * @param throwable the Throwable to possibly propagate
 * @param declaredType1 any checked exception type declared by the callingmethod
 * @param declaredType2 any other checked exception type declared by thecalling method
 */
public static <X1 extends Throwable,X2 extends Throwable>void propagateIfPossible(@Nullable Throwable throwable,Class<X1> declaredType1,Class<X2> declaredType2) throws X1, X2 {
  Preconditions.checkNotNull(declaredType2);
  propagateIfInstanceOf(throwable,declaredType1);
  propagateIfPossible(throwable,declaredType2);
}
