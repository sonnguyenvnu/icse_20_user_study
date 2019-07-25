@Override public Runnable setup(final LoadingComponent loading){
  final AggregationRegistry c=loading.aggregationRegistry();
  final AggregationFactory factory=loading.aggregationFactory();
  return () -> {
    c.register(Empty.NAME,Empty.class,EmptyInstance.class,args -> Empty.INSTANCE);
    c.register(Group.NAME,Group.class,GroupInstance.class,new GroupingAggregationBuilder(factory){
      @Override protected Aggregation build(      Optional<List<String>> over,      Optional<Aggregation> each){
        return Group.create(over,each.map(AggregationOrList::fromAggregation));
      }
    }
);
    c.register(Collapse.NAME,Collapse.class,CollapseInstance.class,new GroupingAggregationBuilder(factory){
      @Override protected Aggregation build(      Optional<List<String>> over,      Optional<Aggregation> each){
        return Collapse.create(over,each);
      }
    }
);
    c.register(Chain.NAME,Chain.class,ChainInstance.class,new AbstractAggregationDSL(factory){
      @Override public Aggregation build(      final AggregationArguments args){
        final List<Aggregation> chain=ImmutableList.copyOf(args.all(FunctionExpression.class).stream().map(this::asAggregation).iterator());
        return new Chain(chain);
      }
    }
);
    c.register(Options.NAME,Options.class,AggregationInstance.class,new AbstractAggregationDSL(factory){
      @Override public Aggregation build(      final AggregationArguments args){
        final Optional<Aggregation> child=args.positionalOrKeyword("aggregation",FunctionExpression.class).map(this::asAggregation);
        final Optional<Duration> size=args.keyword("size",DurationExpression.class).map(DurationExpression::toDuration);
        final Optional<Duration> extent=args.keyword("extent",DurationExpression.class).map(DurationExpression::toDuration);
        final Optional<SamplingQuery> sampling;
        if (size.isPresent() || extent.isPresent()) {
          sampling=Optional.of(new SamplingQuery(size.orElse(null),extent.orElse(null)));
        }
 else {
          sampling=Optional.empty();
        }
        return new Options(sampling,child);
      }
    }
);
  }
;
}
