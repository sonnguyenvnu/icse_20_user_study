private static GenericPropertyBuilder<CommentRequirement> requirementPropertyBuilder(String name,String commentType){
  DESCRIPTOR_NAME_TO_COMMENT_TYPE.put(name,commentType);
  return PropertyFactory.enumProperty(name,CommentRequirement.mappings()).desc(commentType + ". Possible values: " + CommentRequirement.labels()).defaultValue(CommentRequirement.Required);
}
