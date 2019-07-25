@Override protected boolean validate(NumbericRenderData data){
  if (CollectionUtils.isEmpty(data.getNumbers())) {
    logger.debug("Empty NumbericRenderData datamodel: {}",data);
    return false;
  }
  return true;
}
