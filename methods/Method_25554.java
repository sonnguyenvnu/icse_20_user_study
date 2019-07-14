/** 
 * Returns the values of the given symbol's  {@code javax.annotation.Generated} or {@code javax.annotation.processing.Generated} annotation, if present.
 */
public static ImmutableSet<String> getGeneratedBy(ClassSymbol symbol,VisitorState state){
  checkNotNull(symbol);
  Optional<Compound> c=Stream.of("javax.annotation.Generated","javax.annotation.processing.Generated").map(state::getSymbolFromString).filter(a -> a != null).map(symbol::attribute).filter(a -> a != null).findFirst();
  if (!c.isPresent()) {
    return ImmutableSet.of();
  }
  Optional<Attribute> values=c.get().getElementValues().entrySet().stream().filter(e -> e.getKey().getSimpleName().contentEquals("value")).map(e -> e.getValue()).findAny();
  if (!values.isPresent()) {
    return ImmutableSet.of();
  }
  ImmutableSet.Builder<String> suppressions=ImmutableSet.builder();
  values.get().accept(new SimpleAnnotationValueVisitor8<Void,Void>(){
    @Override public Void visitString(    String s,    Void aVoid){
      suppressions.add(s);
      return super.visitString(s,aVoid);
    }
    @Override public Void visitArray(    List<? extends AnnotationValue> vals,    Void aVoid){
      vals.stream().forEachOrdered(v -> v.accept(this,null));
      return super.visitArray(vals,aVoid);
    }
  }
,null);
  return suppressions.build();
}
