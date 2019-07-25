/** 
 * Returns the set of instruments to be used for benchmarking. 
 */
public ImmutableSet<Instrument> instruments(){
  return FluentIterable.from(instrumentedMethods).transform(new Function<InstrumentedMethod,Instrument>(){
    @Override public Instrument apply(    InstrumentedMethod input){
      return input.instrument();
    }
  }
).toSet();
}
