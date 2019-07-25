/** 
 * Initializes the mapping with a new source parameter.
 * @param sourceParameter sets the source parameter that acts as a basis for this mapping
 */
public void init(Parameter sourceParameter){
  if (sourceReference != null) {
    SourceReference oldSourceReference=sourceReference;
    sourceReference=new SourceReference.BuilderFromSourceReference().sourceParameter(sourceParameter).sourceReference(oldSourceReference).build();
  }
}
