private static String toEnum(GraphQLEnumType type){
  String types=type.getValues().stream().map(value -> value.getName()).filter(name -> !name.equals("UNRECOGNIZED")).collect(Collectors.joining(", \n  "));
  return String.format("export enum %s {\n  %s\n}",type.getName() + "Enum",types);
}
