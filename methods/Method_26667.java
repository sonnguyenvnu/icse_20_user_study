/** 
 * Returns either the  {@code Name} bound to the specified label, or a {@code Name} representingthe original label if none is already bound.
 */
@Nullable static Name inlineLabel(@Nullable CharSequence label,Inliner inliner){
  return (label == null) ? null : inliner.asName(inliner.getOptionalBinding(new Key(label)).or(label));
}
