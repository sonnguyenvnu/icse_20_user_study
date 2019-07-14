/** 
 * Converts the given attribute to one or more strings. 
 */
public static Stream<String> asStrings(Attribute v){
  return MoreObjects.firstNonNull(v.accept(new SimpleAnnotationValueVisitor8<Stream<String>,Void>(){
    @Override public Stream<String> visitString(    String s,    Void unused){
      return Stream.of(s);
    }
    @Override public Stream<String> visitArray(    List<? extends AnnotationValue> list,    Void unused){
      return list.stream().flatMap(a -> a.accept(this,null)).filter(x -> x != null);
    }
  }
,null),Stream.empty());
}
