/** 
 * Returns the first alternate whose type is not unknown and is not  {@link org.yinwang.pysonar.Analyzer.idx.builtins.None}.
 * @return the first non-unknown, non-{@code None} alternate, or {@code null} if none found
 */
@Nullable public Type firstUseful(){
  for (  Type type : types) {
    if (!type.isUnknownType() && type != Type.NONE) {
      return type;
    }
  }
  return null;
}
