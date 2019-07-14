/** 
 * Defines many parameters at once from  {@link jodd.props.Props}.
 */
public void defineParameters(final Props props){
  Map<?,?> map=new HashMap<>();
  props.extractProps(map);
  defineParameters(map);
}
