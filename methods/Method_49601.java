private SearchParams convertQuery(Condition<?> condition,final KeyInformation.StoreRetriever information,final LuceneCustomAnalyzer delegatingAnalyzer){
  final SearchParams params=new SearchParams();
  if (condition instanceof PredicateCondition) {
    final PredicateCondition<String,?> atom=(PredicateCondition)condition;
    Object value=atom.getValue();
    final String key=atom.getKey();
    final JanusGraphPredicate janusgraphPredicate=atom.getPredicate();
    if (value instanceof Number) {
      Preconditions.checkArgument(janusgraphPredicate instanceof Cmp,"Relation not supported on numeric types: " + janusgraphPredicate);
      params.addQuery(numericQuery(key,(Cmp)janusgraphPredicate,(Number)value));
    }
 else     if (value instanceof String) {
      if (janusgraphPredicate == Cmp.LESS_THAN) {
        params.addQuery(TermRangeQuery.newStringRange(key,null,value.toString(),false,false));
      }
 else       if (janusgraphPredicate == Cmp.LESS_THAN_EQUAL) {
        params.addQuery(TermRangeQuery.newStringRange(key,null,value.toString(),false,true));
      }
 else       if (janusgraphPredicate == Cmp.GREATER_THAN) {
        params.addQuery(TermRangeQuery.newStringRange(key,value.toString(),null,false,false));
      }
 else       if (janusgraphPredicate == Cmp.GREATER_THAN_EQUAL) {
        params.addQuery(TermRangeQuery.newStringRange(key,value.toString(),null,true,false));
      }
 else {
        final Mapping map=Mapping.getMapping(information.get(key));
        if ((map == Mapping.DEFAULT || map == Mapping.TEXT) && !Text.HAS_CONTAINS.contains(janusgraphPredicate))         throw new IllegalArgumentException("Text mapped string values only support CONTAINS queries and not: " + janusgraphPredicate);
        if (map == Mapping.STRING && Text.HAS_CONTAINS.contains(janusgraphPredicate))         throw new IllegalArgumentException("String mapped string values do not support CONTAINS queries: " + janusgraphPredicate);
        if (janusgraphPredicate == Text.CONTAINS) {
          tokenize(params,map,delegatingAnalyzer,((String)value).toLowerCase(),key,janusgraphPredicate);
        }
 else         if (janusgraphPredicate == Text.CONTAINS_PREFIX) {
          tokenize(params,map,delegatingAnalyzer,(String)value,key,janusgraphPredicate);
        }
 else         if (janusgraphPredicate == Text.PREFIX) {
          params.addQuery(new PrefixQuery(new Term(key,(String)value)));
        }
 else         if (janusgraphPredicate == Text.REGEX) {
          final RegexpQuery rq=new RegexpQuery(new Term(key,(String)value));
          params.addQuery(rq);
        }
 else         if (janusgraphPredicate == Text.CONTAINS_REGEX) {
          final RegexpQuery rq=new RegexpQuery(new Term(key,".*" + (((String)value).toLowerCase()) + ".*"));
          params.addQuery(rq);
        }
 else         if (janusgraphPredicate == Cmp.EQUAL) {
          tokenize(params,map,delegatingAnalyzer,(String)value,key,janusgraphPredicate);
        }
 else         if (janusgraphPredicate == Cmp.NOT_EQUAL) {
          final BooleanQuery.Builder q=new BooleanQuery.Builder();
          q.add(new MatchAllDocsQuery(),BooleanClause.Occur.MUST);
          q.add(new TermQuery(new Term(key,(String)value)),BooleanClause.Occur.MUST_NOT);
          params.addQuery(q.build());
        }
 else         if (janusgraphPredicate == Text.FUZZY) {
          params.addQuery(new FuzzyQuery(new Term(key,(String)value)));
        }
 else         if (janusgraphPredicate == Text.CONTAINS_FUZZY) {
          value=((String)value).toLowerCase();
          final Builder b=new BooleanQuery.Builder();
          for (          final String term : Text.tokenize((String)value)) {
            b.add(new FuzzyQuery(new Term(key,term)),BooleanClause.Occur.MUST);
          }
          params.addQuery(b.build());
        }
 else         throw new IllegalArgumentException("Relation is not supported for string value: " + janusgraphPredicate);
      }
    }
 else     if (value instanceof Geoshape) {
      Preconditions.checkArgument(janusgraphPredicate instanceof Geo,"Relation not supported on geo types: " + janusgraphPredicate);
      final Shape shape=((Geoshape)value).getShape();
      final SpatialOperation spatialOp=SPATIAL_PREDICATES.get(janusgraphPredicate);
      final SpatialArgs args=new SpatialArgs(spatialOp,shape);
      params.addQuery(getSpatialStrategy(key,information.get(key)).makeQuery(args));
    }
 else     if (value instanceof Date) {
      Preconditions.checkArgument(janusgraphPredicate instanceof Cmp,"Relation not supported on date types: " + janusgraphPredicate);
      params.addQuery(numericQuery(key,(Cmp)janusgraphPredicate,((Date)value).getTime()));
    }
 else     if (value instanceof Instant) {
      Preconditions.checkArgument(janusgraphPredicate instanceof Cmp,"Relation not supported on instant types: " + janusgraphPredicate);
      params.addQuery(numericQuery(key,(Cmp)janusgraphPredicate,((Instant)value).toEpochMilli()));
    }
 else     if (value instanceof Boolean) {
      Preconditions.checkArgument(janusgraphPredicate instanceof Cmp,"Relation not supported on boolean types: " + janusgraphPredicate);
      final int intValue;
switch ((Cmp)janusgraphPredicate) {
case EQUAL:
        intValue=((Boolean)value) ? 1 : 0;
      params.addQuery(IntPoint.newRangeQuery(key,intValue,intValue));
    break;
case NOT_EQUAL:
  intValue=((Boolean)value) ? 0 : 1;
params.addQuery(IntPoint.newRangeQuery(key,intValue,intValue));
break;
default :
throw new IllegalArgumentException("Boolean types only support EQUAL or NOT_EQUAL");
}
}
 else if (value instanceof UUID) {
Preconditions.checkArgument(janusgraphPredicate instanceof Cmp,"Relation not supported on UUID types: " + janusgraphPredicate);
if (janusgraphPredicate == Cmp.EQUAL) {
params.addQuery(new TermQuery(new Term(key,value.toString())));
}
 else if (janusgraphPredicate == Cmp.NOT_EQUAL) {
final BooleanQuery.Builder q=new BooleanQuery.Builder();
q.add(new MatchAllDocsQuery(),BooleanClause.Occur.MUST);
q.add(new TermQuery(new Term(key,value.toString())),BooleanClause.Occur.MUST_NOT);
params.addQuery(q.build());
}
 else {
throw new IllegalArgumentException("Relation is not supported for UUID type: " + janusgraphPredicate);
}
}
 else {
throw new IllegalArgumentException("Unsupported type: " + value);
}
}
 else if (condition instanceof Not) {
final SearchParams childParams=convertQuery(((Not)condition).getChild(),information,delegatingAnalyzer);
params.addQuery(new MatchAllDocsQuery(),BooleanClause.Occur.MUST);
params.addParams(childParams,BooleanClause.Occur.MUST_NOT);
}
 else if (condition instanceof And) {
for (final Condition c : condition.getChildren()) {
final SearchParams childParams=convertQuery(c,information,delegatingAnalyzer);
params.addParams(childParams,BooleanClause.Occur.MUST);
}
}
 else if (condition instanceof Or) {
for (final Condition c : condition.getChildren()) {
final SearchParams childParams=convertQuery(c,information,delegatingAnalyzer);
params.addParams(childParams,BooleanClause.Occur.SHOULD);
}
}
 else throw new IllegalArgumentException("Invalid condition: " + condition);
return params;
}
