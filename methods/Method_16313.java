@Override public Optional<String> parse(String value,Object context){
  if (value == null) {
    return Optional.empty();
  }
  StringJoiner joiner=targetFormat.createJoiner();
  List<DictMapping> dictMappings=formatter.format(sourceFormat,value,(key,pattern) -> {
    DictMapping dictMapping=mapping.get(key);
    if (dictMapping == null) {
      return null;
    }
    dictMapping=dictMapping.clone();
    dictMapping.setDefaultVar(Collections.singletonMap("pattern",pattern));
    return dictMapping;
  }
).stream().filter(Objects::nonNull).map(FormatterResult::getResult).collect(Collectors.toList());
  Set<String> notAppendList=new HashSet<>();
  List<String> mappingResult=dictMappings.stream().filter(Objects::nonNull).peek(dictMapping -> dictMapping.filterChildren((mapping -> {
    String strVal=mapping.getValue();
    notAppendList.add(strVal);
    int index=dictMappings.indexOf(mappingOfValue(strVal));
    DictMapping tmp=null;
    if (-1 != index) {
      tmp=dictMappings.get(index);
    }
    if (null != tmp) {
      mapping.setDefaultVar(tmp.getDefaultVar());
    }
    return null != tmp;
  }
))).filter(mapping -> !notAppendList.contains(mapping.getValue())).map(dict -> dict.toString(context)).collect(Collectors.toList());
  mappingResult.forEach(joiner::add);
  return Optional.ofNullable(joiner.toString());
}
