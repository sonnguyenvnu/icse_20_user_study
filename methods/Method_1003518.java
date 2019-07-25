private Object execute(ElasticsearchParameterAccessor parameterAccessor){
  ResultProcessor processor=queryMethod.getResultProcessor().withDynamicProjection(parameterAccessor);
  Query query=createQuery(new ConvertingParameterAccessor(elasticsearchOperations.getElasticsearchConverter(),parameterAccessor));
  Class<?> typeToRead=processor.getReturnedType().getTypeToRead();
  String indexName=queryMethod.getEntityInformation().getIndexName();
  String indexTypeName=queryMethod.getEntityInformation().getIndexTypeName();
  ReactiveElasticsearchQueryExecution execution=getExecution(parameterAccessor,new ResultProcessingConverter(processor,elasticsearchOperations));
  return execution.execute(query,processor.getReturnedType().getDomainType(),indexName,indexTypeName,typeToRead);
}
