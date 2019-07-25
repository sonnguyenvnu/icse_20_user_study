@SuppressWarnings("unchecked") protected Class<F> type(){
  return (Class<F>)testDefault(new StaticFeatureSet()).getClass();
}
