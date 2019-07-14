void addAttributeGroup(String groupName,List<AttributeInfo> attributes) throws EpoxyProcessorException {
  AttributeInfo defaultAttribute=null;
  for (  AttributeInfo attribute : attributes) {
    if (attribute.isRequired() || (attribute.getCodeToSetDefault().isEmpty() && !hasDefaultKotlinValue(attribute))) {
      continue;
    }
    boolean hasSetExplicitDefault=defaultAttribute != null && hasExplicitDefault(defaultAttribute);
    if (hasSetExplicitDefault && hasExplicitDefault(attribute)) {
      throw buildEpoxyException("Only one default value can exist for a group of attributes: " + attributes);
    }
    if (hasSetExplicitDefault) {
      continue;
    }
    if (defaultAttribute == null || hasExplicitDefault(attribute) || attribute.hasSetNullability()) {
      defaultAttribute=attribute;
    }
  }
  AttributeGroup group=new AttributeGroup(groupName,attributes,defaultAttribute);
  attributeGroups.add(group);
  for (  AttributeInfo attribute : attributes) {
    attribute.setAttributeGroup(group);
  }
}
