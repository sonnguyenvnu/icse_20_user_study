public String parseWithBean(final String template,final Object context){
  return super.parse(template,macroName -> {
    Object value=BeanUtil.declaredSilent.getProperty(context,macroName);
    if (value == null) {
      return null;
    }
    return value.toString();
  }
);
}
