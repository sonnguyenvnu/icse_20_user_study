private String createMessage(Collection<CollapsedRequest<Object,Object>> requests,List<Object> response){
  return ERROR_MSG + arrayFormat(ERROR_MSF_TEMPLATE,new Object[]{getCollapserKey().name(),requests.size(),response.size()}).getMessage();
}
