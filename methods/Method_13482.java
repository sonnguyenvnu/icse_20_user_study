public List<String> toList(String json){
  List<String> list=Collections.emptyList();
  try {
    if (StringUtils.hasText(json)) {
      list=objectMapper.readValue(json,List.class);
    }
  }
 catch (  IOException e) {
    if (logger.isErrorEnabled()) {
      logger.error(e.getMessage(),e);
    }
  }
  return list;
}
