private static String constructMsg(String errorMessage,List<BTCMarketsException> responses){
  final StringBuilder sb=new StringBuilder();
  if (errorMessage != null) {
    sb.append(errorMessage).append(": ");
  }
  if (responses != null) {
    for (    BTCMarketsException response : responses) {
      if (!Boolean.TRUE.equals(response.getSuccess())) {
        sb.append(String.format("Id %d: %s",response.getId(),response.getMessage()));
      }
    }
  }
  return sb.toString();
}
