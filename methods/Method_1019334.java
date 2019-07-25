@Override public void initialize(InEnum annotation){
  IntArrayValuable[] values=annotation.value().getEnumConstants();
  if (values.length == 0) {
    this.values=Collections.emptyList();
  }
 else {
    this.values=Arrays.stream(values[0].array()).boxed().collect(Collectors.toList());
  }
}
