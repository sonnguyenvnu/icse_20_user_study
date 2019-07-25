public static FilterRegistry registry(){
  final FilterRegistry registry=new FilterRegistry();
  registry.registerList(AndFilter.OPERATOR,AndFilter.class,new MultiArgumentsFilterBase<>(AndFilter::create,AndFilter::filters,filter()));
  registry.registerList(OrFilter.OPERATOR,OrFilter.class,new MultiArgumentsFilterBase<>(OrFilter::create,OrFilter::filters,filter()));
  registry.registerOne(NotFilter.OPERATOR,NotFilter.class,new OneArgumentFilterEncoding<>(NotFilter::create,NotFilter::filter,filter()));
  registry.registerTwo(MatchKeyFilter.OPERATOR,MatchKeyFilter.class,new OneArgumentFilterEncoding<>(MatchKeyFilter::create,MatchKeyFilter::key,string()));
  registry.registerTwo(MatchTagFilter.OPERATOR,MatchTagFilter.class,new TwoArgumentFilterEncoding<>(MatchTagFilter::create,MatchTagFilter::tag,MatchTagFilter::value,string(),string()));
  registry.registerOne(HasTagFilter.OPERATOR,HasTagFilter.class,new OneArgumentFilterEncoding<>(HasTagFilter::create,HasTagFilter::tag,string()));
  registry.registerTwo(StartsWithFilter.OPERATOR,StartsWithFilter.class,new TwoArgumentFilterEncoding<>(StartsWithFilter::create,StartsWithFilter::tag,StartsWithFilter::value,string(),string()));
  registry.registerTwo(RegexFilter.OPERATOR,RegexFilter.class,new TwoArgumentFilterEncoding<>(RegexFilter::create,RegexFilter::tag,RegexFilter::value,string(),string()));
  registry.registerEmpty(TrueFilter.OPERATOR,TrueFilter.class,new NoArgumentFilterBase<>(TrueFilter::get));
  registry.registerEmpty(FalseFilter.OPERATOR,FalseFilter.class,new NoArgumentFilterBase<>(FalseFilter::get));
  registry.registerOne(RawFilter.OPERATOR,RawFilter.class,new OneArgumentFilterEncoding<>(RawFilter::create,RawFilter::filter,string()));
  return registry;
}
