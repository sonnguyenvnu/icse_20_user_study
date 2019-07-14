/** 
 * Returns the  {@link Class} instance for the {@link Matcher} associated with the provided {@link Matches} annotation. This roundabout solution is recommended and explained by {@link Element#getAnnotation(Class)}.
 */
static Class<? extends Matcher<? super ExpressionTree>> getValue(Matches matches){
  String name;
  try {
    matches.value();
    throw new RuntimeException("unreachable");
  }
 catch (  MirroredTypeException e) {
    DeclaredType type=(DeclaredType)e.getTypeMirror();
    name=((TypeElement)type.asElement()).getQualifiedName().toString();
  }
  try {
    return asSubclass(Class.forName(name),new TypeToken<Matcher<? super ExpressionTree>>(){
    }
);
  }
 catch (  ClassNotFoundException|ClassCastException e) {
    throw new RuntimeException(e);
  }
}
