private static ImmutableSet<String> getPathsForProto(String prefix,Selection node,Descriptor descriptor,Map<String,FragmentDefinition> fragmentsByName){
  ImmutableSet.Builder<String> builder=ImmutableSet.builder();
  if (node instanceof Field) {
    Field field=((Field)node);
    String name=FIELD_TO_PROTO.convert(field.getName());
    if (descriptor.findFieldByName(name) == null) {
      if (!prefix.isEmpty()) {
        builder.add(prefix + "*");
      }
      return builder.build();
    }
    if (field.getSelectionSet() != null) {
      for (      Selection selection : field.getSelectionSet().getSelections()) {
        builder.addAll(getPathsForProto(prefix + name + ".",selection,descriptor.findFieldByName(name).getMessageType(),fragmentsByName));
      }
    }
 else {
      builder.add(prefix + name);
    }
  }
 else   if (node instanceof FragmentSpread) {
    FragmentSpread fragmentSpread=(FragmentSpread)node;
    String name=fragmentSpread.getName();
    FragmentDefinition field=fragmentsByName.get(fragmentSpread.getName());
    if (field.getSelectionSet() != null) {
      for (      Selection selection : field.getSelectionSet().getSelections()) {
        builder.addAll(getPathsForProto(prefix,selection,descriptor,fragmentsByName));
      }
    }
 else {
      builder.add(prefix + name);
    }
  }
 else {
  }
  return builder.build();
}
