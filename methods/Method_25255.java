/** 
 * Determines field nullness based on store and annotations. 
 */
Nullness standardFieldNullness(ClassAndField accessed,@Nullable AccessPath path,AccessPathValues<Nullness> store){
  Nullness dataflowResult=(path == null) ? BOTTOM : store.valueOfAccessPath(path,BOTTOM);
  if (dataflowResult != BOTTOM) {
    return dataflowResult;
  }
  Optional<Nullness> declaredNullness=NullnessAnnotations.fromAnnotationsOn(accessed.symbol);
  if (!declaredNullness.isPresent()) {
    Type ftype=accessed.symbol.type;
    if (ftype instanceof TypeVariable) {
      declaredNullness=NullnessAnnotations.getUpperBound((TypeVariable)ftype);
    }
 else {
      declaredNullness=NullnessAnnotations.fromDefaultAnnotations(accessed.symbol);
    }
  }
  return declaredNullness.orElse(defaultAssumption);
}
