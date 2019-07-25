/** 
 * Returns a fully constructed  {@link CompositeItemProcessor}.
 * @return a new {@link CompositeItemProcessor}
 */
public CompositeItemProcessor<I,O> build(){
  Assert.notNull(delegates,"A list of delegates is required.");
  Assert.notEmpty(delegates,"The delegates list must have one or more delegates.");
  CompositeItemProcessor<I,O> processor=new CompositeItemProcessor<>();
  processor.setDelegates(this.delegates);
  return processor;
}
