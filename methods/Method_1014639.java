@Override protected boolean validate(MiniTableRenderData data){
  if (!(data).isSetBody() && !(data).isSetHeader()) {
    logger.debug("Empty MiniTableRenderData datamodel: {}",data);
    return false;
  }
  return true;
}
