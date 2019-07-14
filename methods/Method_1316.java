/** 
 * Propagates  {@code throwable} exactly as-is, if and only if it is aninstance of  {@link RuntimeException} or {@link Error}.  Example usage: <pre> try { someMethodThatCouldThrowAnything(); } catch (IKnowWhatToDoWithThisException e) { handle(e); } catch (Throwable t) { Throwables.propagateIfPossible(t); throw new RuntimeException("unexpected", t); } </pre>
 */
public static void propagateIfPossible(@Nullable Throwable throwable){
  propagateIfInstanceOf(throwable,Error.class);
  propagateIfInstanceOf(throwable,RuntimeException.class);
}
