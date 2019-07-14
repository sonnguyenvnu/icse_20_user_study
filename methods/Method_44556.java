private BigDecimal toBigDecimal(JsonNode elem) throws JsonProcessingException {
  try {
    return new BigDecimal(elem.asText());
  }
 catch (  NumberFormatException e) {
    throw new JsonParseException(elem.traverse(),"Expected decimal",e);
  }
}
