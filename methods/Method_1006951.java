/** 
 * Returns a fully constructed  {@link ClassifierCompositeItemWriter}.
 * @return a new {@link ClassifierCompositeItemWriter}
 */
public ClassifierCompositeItemWriter<T> build(){
  Assert.notNull(classifier,"A classifier is required.");
  ClassifierCompositeItemWriter<T> writer=new ClassifierCompositeItemWriter<>();
  writer.setClassifier(this.classifier);
  return writer;
}
