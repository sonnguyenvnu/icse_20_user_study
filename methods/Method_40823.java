public static void checkDup1(Node pattern,Set<String> seen){
  if (pattern instanceof Name) {
    String id=((Name)pattern).id;
    if (seen.contains(id)) {
      _.abort(pattern,"duplicated name found in pattern: " + pattern);
    }
 else {
      seen.add(id);
    }
  }
 else   if (pattern instanceof RecordLiteral) {
    for (    Node v : ((RecordLiteral)pattern).map.values()) {
      checkDup1(v,seen);
    }
  }
 else   if (pattern instanceof VectorLiteral) {
    for (    Node v : ((VectorLiteral)pattern).elements) {
      checkDup1(v,seen);
    }
  }
}
