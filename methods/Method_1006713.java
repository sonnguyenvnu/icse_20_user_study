/** 
 * Find the subset of the  {@link AnnotationInfo} objects in this list for which the given filter predicate istrue.
 * @param filter The  {@link AnnotationInfoFilter} to apply.
 * @return The subset of the {@link AnnotationInfo} objects in this list for which the given filter predicate istrue.
 */
public AnnotationInfoList filter(final AnnotationInfoFilter filter){
  final AnnotationInfoList annotationInfoFiltered=new AnnotationInfoList();
  for (  final AnnotationInfo resource : this) {
    if (filter.accept(resource)) {
      annotationInfoFiltered.add(resource);
    }
  }
  return annotationInfoFiltered;
}
