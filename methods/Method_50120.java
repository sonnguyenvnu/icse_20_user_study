private static String toEnum(GraphQLEnumType type){
  ArrayList<String> values=new ArrayList<>();
  int i=0;
  for (  GraphQLEnumValueDefinition value : type.getValues()) {
    if (value.getName().equals("UNRECOGNIZED")) {
      continue;
    }
    values.add(String.format("%s = %d;",value.getName(),i));
    i++;
  }
  return String.format("message %s {\n %s\n enum Enum {\n%s\n}\n}",type.getName(),getJspb(type),Joiner.on("\n").join(values));
}
