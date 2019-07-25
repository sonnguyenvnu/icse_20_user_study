public IteratorOptions build(){
  if (!namedOptions.isEmpty()) {
    options.setNamedOptions(namedOptions);
  }
  return options;
}
