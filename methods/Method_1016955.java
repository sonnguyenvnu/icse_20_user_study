@Override public Runnable setup(LoadingComponent loading){
  final AggregationRegistry c=loading.aggregationRegistry();
  final AggregationFactory factory=loading.aggregationFactory();
  return () -> {
    c.register(CardinalityAggregation.NAME,CardinalityAggregation.class,CardinalityInstance.class,new SamplingAggregationDSL<CardinalityAggregation>(factory){
      @Override protected CardinalityAggregation buildWith(      final AggregationArguments args,      final Optional<Duration> size,      final Optional<Duration> extent){
        final CardinalityMethod method=args.positionalOrKeyword("method",Expression.class).map(CardinalityMethod.Companion::fromExpression).orElse(null);
        return new CardinalityAggregation(null,size.orElse(null),extent.orElse(null),method);
      }
    }
);
    c.registerInstance(DistributedCardinalityInstance.NAME,DistributedCardinalityInstance.class);
  }
;
}
