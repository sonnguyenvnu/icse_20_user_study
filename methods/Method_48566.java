private static Object string2ElementId(String str){
  if (str.contains(RelationIdentifier.TOSTRING_DELIMITER))   return RelationIdentifier.parse(str);
 else   return name2LongID(str);
}
