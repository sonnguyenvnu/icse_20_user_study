/** 
 * Returns the value of the annotation element-value pair with the given name if it is not explicitly set.
 */
public static Optional<Attribute> getValue(Attribute.Compound attribute,String name){
  return attribute.getElementValues().entrySet().stream().filter(e -> e.getKey().getSimpleName().contentEquals(name)).map(Map.Entry::getValue).findFirst();
}
