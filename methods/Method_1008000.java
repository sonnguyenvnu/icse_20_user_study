public static JavaVersion parse(String value){
  Objects.requireNonNull(value);
  if (!isValid(value)) {
    throw new IllegalArgumentException("value");
  }
  List<Integer> version=new ArrayList<>();
  String[] components=value.split("\\.");
  for (  String component : components) {
    version.add(Integer.valueOf(component));
  }
  return new JavaVersion(version);
}
