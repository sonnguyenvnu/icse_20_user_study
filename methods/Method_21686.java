private AggregationBuilder addFieldToAgg(MethodField field,ValuesSourceAggregationBuilder builder){
  KVValue kvValue=field.getParams().get(0);
  if (kvValue.key != null && kvValue.key.equals("script")) {
    if (kvValue.value instanceof MethodField) {
      return builder.script(new Script(((MethodField)kvValue.value).getParams().get(1).toString()));
    }
 else {
      return builder.script(new Script(kvValue.value.toString()));
    }
  }
 else   if (kvValue.key != null && kvValue.value.toString().trim().startsWith("def")) {
    return builder.script(new Script(kvValue.value.toString()));
  }
 else   if (kvValue.key != null && (kvValue.key.equals("nested") || kvValue.key.equals("reverse_nested"))) {
    NestedType nestedType=(NestedType)kvValue.value;
    builder.field(nestedType.field);
    AggregationBuilder nestedBuilder;
    String nestedAggName=nestedType.field + "@NESTED";
    if (nestedType.isReverse()) {
      if (nestedType.path != null && nestedType.path.startsWith("~")) {
        String realPath=nestedType.path.substring(1);
        nestedBuilder=AggregationBuilders.nested(nestedAggName,realPath);
        nestedBuilder=nestedBuilder.subAggregation(builder);
        return AggregationBuilders.reverseNested(nestedAggName + "_REVERSED").subAggregation(nestedBuilder);
      }
 else {
        ReverseNestedAggregationBuilder reverseNestedAggregationBuilder=AggregationBuilders.reverseNested(nestedAggName);
        if (nestedType.path != null) {
          reverseNestedAggregationBuilder.path(nestedType.path);
        }
        nestedBuilder=reverseNestedAggregationBuilder;
      }
    }
 else {
      nestedBuilder=AggregationBuilders.nested(nestedAggName,nestedType.path);
    }
    return nestedBuilder.subAggregation(builder);
  }
 else   if (kvValue.key != null && (kvValue.key.equals("children"))) {
    ChildrenType childrenType=(ChildrenType)kvValue.value;
    builder.field(childrenType.field);
    AggregationBuilder childrenBuilder;
    String childrenAggName=childrenType.field + "@CHILDREN";
    childrenBuilder=JoinAggregationBuilders.children(childrenAggName,childrenType.childType);
    return childrenBuilder;
  }
  return builder.field(kvValue.toString());
}
