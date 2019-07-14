private static String constructComment(DescriptorProtos.SourceCodeInfo.Location location){
  String trailingComment=location.getTrailingComments().trim();
  return (location.getLeadingComments().trim() + (!trailingComment.isEmpty() ? (". " + trailingComment) : "")).replaceAll("^[*\\\\/.]*\\s*","");
}
