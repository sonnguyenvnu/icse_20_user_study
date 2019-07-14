public static void checkTypeName(JanusGraphSchemaCategory category,String name){
switch (category) {
case EDGELABEL:
case VERTEXLABEL:
    if (name == null)     throw Element.Exceptions.labelCanNotBeNull();
  if (StringUtils.isBlank(name))   throw Element.Exceptions.labelCanNotBeEmpty();
break;
case PROPERTYKEY:
if (name == null) throw Property.Exceptions.propertyKeyCanNotBeNull();
if (StringUtils.isBlank(name)) throw Property.Exceptions.propertyKeyCanNotBeEmpty();
break;
case GRAPHINDEX:
Preconditions.checkArgument(StringUtils.isNotBlank(name),"Index name cannot be empty: %s",name);
break;
default :
throw new AssertionError(category);
}
}
