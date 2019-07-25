public JcrAllowedActions copy(Node allowedNode,Principal principal,String... privilegeNames){
  return copy(allowedNode,false,principal,privilegeNames);
}
