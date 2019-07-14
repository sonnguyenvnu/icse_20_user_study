public String parseWithMap(final String template,final Map map){
  return super.parse(template,macroName -> {
    Object value=map.get(macroName);
    if (value == null) {
      return null;
    }
    return value.toString();
  }
);
}
