@Override void flatten(Query sourceQuery,IndexReader reader,Collection<Query> flatQueries,float boost) throws IOException {
  if (sourceQuery instanceof BoostQuery) {
    BoostQuery bq=(BoostQuery)sourceQuery;
    sourceQuery=bq.getQuery();
    boost*=bq.getBoost();
    flatten(sourceQuery,reader,flatQueries,boost);
  }
 else   if (sourceQuery instanceof SpanTermQuery) {
    super.flatten(new TermQuery(((SpanTermQuery)sourceQuery).getTerm()),reader,flatQueries,boost);
  }
 else   if (sourceQuery instanceof ConstantScoreQuery) {
    flatten(((ConstantScoreQuery)sourceQuery).getQuery(),reader,flatQueries,boost);
  }
 else   if (sourceQuery instanceof FunctionScoreQuery) {
    flatten(((FunctionScoreQuery)sourceQuery).getSubQuery(),reader,flatQueries,boost);
  }
 else   if (sourceQuery instanceof MultiPhrasePrefixQuery) {
    flatten(sourceQuery.rewrite(reader),reader,flatQueries,boost);
  }
 else   if (sourceQuery instanceof MultiPhraseQuery) {
    MultiPhraseQuery q=((MultiPhraseQuery)sourceQuery);
    convertMultiPhraseQuery(0,new int[q.getTermArrays().length],q,q.getTermArrays(),q.getPositions(),reader,flatQueries);
  }
 else   if (sourceQuery instanceof BlendedTermQuery) {
    final BlendedTermQuery blendedTermQuery=(BlendedTermQuery)sourceQuery;
    flatten(blendedTermQuery.rewrite(reader),reader,flatQueries,boost);
  }
 else   if (sourceQuery instanceof BoostingQuery) {
    BoostingQuery boostingQuery=(BoostingQuery)sourceQuery;
    flatten(boostingQuery.getMatch(),reader,flatQueries,boost);
    flatten(boostingQuery.getContext(),reader,flatQueries,boostingQuery.getBoost());
  }
 else   if (sourceQuery instanceof SynonymQuery) {
    SynonymQuery synQuery=(SynonymQuery)sourceQuery;
    for (    Term term : synQuery.getTerms()) {
      flatten(new TermQuery(term),reader,flatQueries,boost);
    }
  }
 else   if (sourceQuery instanceof ESToParentBlockJoinQuery) {
    Query childQuery=((ESToParentBlockJoinQuery)sourceQuery).getChildQuery();
    if (childQuery != null) {
      flatten(childQuery,reader,flatQueries,boost);
    }
  }
 else {
    super.flatten(sourceQuery,reader,flatQueries,boost);
  }
}
