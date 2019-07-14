private static String getJspb(GraphQLType type){
  return String.format("option (jspb.message_id) = \"graphql.%s\";",type.getName());
}
