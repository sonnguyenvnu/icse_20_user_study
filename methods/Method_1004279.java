private static <E>E deserialize(@NotNull UnmarshallingContext unmarshallingContext,@NotNull HierarchicalStreamReader reader){
switch (reader.getNodeName()) {
case "java.util.Collections$EmptySet":
    return (E)Collections.emptySet();
case "java.util.Collections$EmptyList":
  return (E)Collections.emptyList();
case "java.util.Collections$EmptyMap":
case "java.util.Collections.EmptyMap":
return (E)Collections.emptyMap();
}
return (E)unmarshallingContext.convertAnother(null,forName(reader.getNodeName()));
}
