@SuppressWarnings("all") private List<EnumDict> getAllOption(RDBColumnMetaData column){
  Class type=column.getJavaType();
  if (null != type) {
    if (type.isArray()) {
      type=type.getComponentType();
    }
    if (type.isEnum() && EnumDict.class.isAssignableFrom(type)) {
      return (List)Arrays.asList(type.getEnumConstants());
    }
  }
  OptionConverter converter=column.getOptionConverter();
  if (converter == null) {
    return new ArrayList<>();
  }
  return (List)converter.getOptions();
}
