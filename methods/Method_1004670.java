/** 
 * Resolve the state of this instance to a  {@link ResolvedProjectDescription}.
 * @return an immutable description.
 */
public ResolvedProjectDescription resolve(){
  return new ResolvedProjectDescription(this);
}
