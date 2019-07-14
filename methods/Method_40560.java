/** 
 * Returns the first alternate whose type is not unknown and is not  {@link Indexer.idx.builtins.None}.
 * @return the first non-unknown, non-{@code None} alternate, or {@code null} if none found
 */
public Type firstUseful(){
  for (  Type type : types) {
    if (!type.isUnknownType() && type != Indexer.idx.builtins.None) {
      return type;
    }
  }
  return null;
}
