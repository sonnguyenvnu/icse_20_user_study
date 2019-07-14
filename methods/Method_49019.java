public static RelationIdentifier parse(String id){
  String[] elements=id.split(TOSTRING_DELIMITER);
  if (elements.length != 3 && elements.length != 4)   throw new IllegalArgumentException("Not a valid relation identifier: " + id);
  try {
    return new RelationIdentifier(LongEncoding.decode(elements[1]),LongEncoding.decode(elements[2]),LongEncoding.decode(elements[0]),elements.length == 4 ? LongEncoding.decode(elements[3]) : 0);
  }
 catch (  NumberFormatException e) {
    throw new IllegalArgumentException("Invalid id - each token expected to be a number",e);
  }
}
