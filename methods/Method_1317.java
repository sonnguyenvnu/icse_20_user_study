/** 
 * Propagates  {@code throwable} exactly as-is, if and only if it is aninstance of  {@link RuntimeException},  {@link Error}, or {@code declaredType}. Example usage: <pre> try { someMethodThatCouldThrowAnything(); } catch (IKnowWhatToDoWithThisException e) { handle(e); } catch (Throwable t) { Throwables.propagateIfPossible(t, OtherException.class); throw new RuntimeException("unexpected", t); } </pre>
 * @param throwable the Throwable to possibly propagate
 * @param declaredType the single checked exception type declared by thecalling method
 */
public static <X extends Throwable>void propagateIfPossible(@Nullable Throwable throwable,Class<X> declaredType) throws X {
  propagateIfInstanceOf(throwable,declaredType);
  propagateIfPossible(throwable);
}
