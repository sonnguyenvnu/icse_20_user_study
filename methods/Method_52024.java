/** 
 * Returns the class specific part of the name. It identifies a class in the namespace it's declared in. If the class is nested inside another, then the list returned contains all enclosing classes in order, from outermost to innermost. <p> {@literal @NotNull}
 * @return The class names.
 */
public List<String> getClassList(){
  return classes.reverse();
}
