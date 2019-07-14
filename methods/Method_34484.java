private void validateMetaHolder(MetaHolder metaHolder){
  Validate.notNull(metaHolder,"metaHolder is required parameter and cannot be null");
  Validate.isTrue(metaHolder.isCommandAnnotationPresent(),"hystrixCommand annotation is absent");
}
