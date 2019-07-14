/** 
 * Propagates  {@code throwable} exactly as-is, if and only if it is aninstance of  {@code declaredType}.  Example usage: <pre> try { someMethodThatCouldThrowAnything(); } catch (IKnowWhatToDoWithThisException e) { handle(e); } catch (Throwable t) { Throwables.propagateIfInstanceOf(t, IOException.class); Throwables.propagateIfInstanceOf(t, SQLException.class); throw Throwables.propagate(t); } </pre>
 */
public static <X extends Throwable>void propagateIfInstanceOf(@Nullable Throwable throwable,Class<X> declaredType) throws X {
  if (throwable != null && declaredType.isInstance(throwable)) {
    throw declaredType.cast(throwable);
  }
}
