boolean hasRelayNode(){
  return mapping.values().stream().anyMatch(type -> type instanceof GraphQLObjectType && ((GraphQLObjectType)type).getInterfaces().contains(nodeInterface));
}
