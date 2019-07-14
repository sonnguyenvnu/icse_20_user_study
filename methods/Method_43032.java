void checkError(BitbayBaseResponse response){
  if (response.getStatus().equalsIgnoreCase("fail")) {
    List<String> errors=response.getErrors();
    if (errors == null || errors.isEmpty()) {
      throw new ExchangeException("Bitbay API unexpected error");
    }
    Iterator<String> errorsIterator=errors.iterator();
    StringBuilder message=new StringBuilder("Bitbay API error: ").append(errorsIterator.next());
    while (errorsIterator.hasNext()) {
      message.append(", ").append(errorsIterator.next());
    }
    throw new ExchangeException(message.toString());
  }
}
