public static String serialize(List<ParsedEntryLink> list){
  return String.join(SEPARATOR,list.stream().map(link -> link.getKey()).collect(Collectors.toList()));
}
