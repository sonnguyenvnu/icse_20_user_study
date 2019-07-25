/** 
 * @param bd {@link AbstractBeanDefinition} instance of the containing bean.
 * @param element the element to parse
 * @param parserContext the context to use
 * @param underspecified if true, a fatal error will not be raised if attributeor element is missing.
 */
protected void parse(Element element,AbstractBeanDefinition bd,ParserContext parserContext,boolean underspecified){
  MutablePropertyValues propertyValues=bd.getPropertyValues();
  propertyValues.addPropertyValue("hasChunkElement",Boolean.TRUE);
  handleItemHandler(bd,"reader","itemReader",ITEM_READER_ADAPTER_CLASS,true,element,parserContext,propertyValues,underspecified);
  handleItemHandler(bd,"processor","itemProcessor",ITEM_PROCESSOR_ADAPTER_CLASS,false,element,parserContext,propertyValues,underspecified);
  handleItemHandler(bd,"writer","itemWriter",ITEM_WRITER_ADAPTER_CLASS,true,element,parserContext,propertyValues,underspecified);
  String commitInterval=element.getAttribute(COMMIT_INTERVAL_ATTR);
  if (StringUtils.hasText(commitInterval)) {
    if (commitInterval.startsWith("#")) {
      BeanDefinitionBuilder completionPolicy=BeanDefinitionBuilder.genericBeanDefinition(SimpleCompletionPolicy.class);
      completionPolicy.addConstructorArgValue(commitInterval);
      completionPolicy.setScope("step");
      propertyValues.addPropertyValue("chunkCompletionPolicy",completionPolicy.getBeanDefinition());
    }
 else {
      propertyValues.addPropertyValue("commitInterval",commitInterval);
    }
  }
  String completionPolicyRef=element.getAttribute(CHUNK_COMPLETION_POLICY_ATTR);
  if (StringUtils.hasText(completionPolicyRef)) {
    RuntimeBeanReference completionPolicy=new RuntimeBeanReference(completionPolicyRef);
    propertyValues.addPropertyValue("chunkCompletionPolicy",completionPolicy);
  }
  if (!underspecified && propertyValues.contains("commitInterval") == propertyValues.contains("chunkCompletionPolicy")) {
    if (propertyValues.contains("commitInterval")) {
      parserContext.getReaderContext().error("The <" + element.getNodeName() + "/> element must contain either '" + COMMIT_INTERVAL_ATTR + "' " + "or '" + CHUNK_COMPLETION_POLICY_ATTR + "', but not both.",element);
    }
 else {
      parserContext.getReaderContext().error("The <" + element.getNodeName() + "/> element must contain either '" + COMMIT_INTERVAL_ATTR + "' " + "or '" + CHUNK_COMPLETION_POLICY_ATTR + "'.",element);
    }
  }
  String skipLimit=element.getAttribute("skip-limit");
  ManagedMap<TypedStringValue,Boolean> skippableExceptions=new ExceptionElementParser().parse(element,parserContext,"skippable-exception-classes");
  if (StringUtils.hasText(skipLimit)) {
    if (skippableExceptions == null) {
      skippableExceptions=new ManagedMap<>();
      skippableExceptions.setMergeEnabled(true);
    }
    propertyValues.addPropertyValue("skipLimit",skipLimit);
  }
  if (skippableExceptions != null) {
    List<Element> exceptionClassElements=DomUtils.getChildElementsByTagName(element,"skippable-exception-classes");
    if (!CollectionUtils.isEmpty(exceptionClassElements)) {
      skippableExceptions.setMergeEnabled(exceptionClassElements.get(0).hasAttribute(MERGE_ATTR) && Boolean.valueOf(exceptionClassElements.get(0).getAttribute(MERGE_ATTR)));
    }
    propertyValues.addPropertyValue("skippableExceptionClasses",skippableExceptions);
  }
  handleItemHandler(bd,"skip-policy","skipPolicy",null,false,element,parserContext,propertyValues,underspecified);
  String retryLimit=element.getAttribute("retry-limit");
  ManagedMap<TypedStringValue,Boolean> retryableExceptions=new ExceptionElementParser().parse(element,parserContext,"retryable-exception-classes");
  if (StringUtils.hasText(retryLimit)) {
    if (retryableExceptions == null) {
      retryableExceptions=new ManagedMap<>();
      retryableExceptions.setMergeEnabled(true);
    }
    propertyValues.addPropertyValue("retryLimit",retryLimit);
  }
  if (retryableExceptions != null) {
    List<Element> exceptionClassElements=DomUtils.getChildElementsByTagName(element,"retryable-exception-classes");
    if (!CollectionUtils.isEmpty(exceptionClassElements)) {
      retryableExceptions.setMergeEnabled(exceptionClassElements.get(0).hasAttribute(MERGE_ATTR) && Boolean.valueOf(exceptionClassElements.get(0).getAttribute(MERGE_ATTR)));
    }
    propertyValues.addPropertyValue("retryableExceptionClasses",retryableExceptions);
  }
  handleItemHandler(bd,"retry-policy","retryPolicy",null,false,element,parserContext,propertyValues,underspecified);
  String cacheCapacity=element.getAttribute("cache-capacity");
  if (StringUtils.hasText(cacheCapacity)) {
    propertyValues.addPropertyValue("cacheCapacity",cacheCapacity);
  }
  String isReaderTransactionalQueue=element.getAttribute("reader-transactional-queue");
  if (StringUtils.hasText(isReaderTransactionalQueue)) {
    propertyValues.addPropertyValue("isReaderTransactionalQueue",isReaderTransactionalQueue);
  }
  String isProcessorTransactional=element.getAttribute("processor-transactional");
  if (StringUtils.hasText(isProcessorTransactional)) {
    propertyValues.addPropertyValue("processorTransactional",isProcessorTransactional);
  }
  handleRetryListenersElement(element,propertyValues,parserContext,bd);
  handleStreamsElement(element,propertyValues,parserContext);
  stepListenerParser.handleListenersElement(element,bd,parserContext);
}
