@Override public IndexQueryBuilder addParameters(Iterable<Parameter> paras){
  Iterables.addAll(parameters,paras);
  return this;
}
