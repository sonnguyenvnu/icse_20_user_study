@Nullable @Override public String apply(BiomeType input){
  BiomeData data=registry.getData(input);
  if (data != null) {
    return data.getName();
  }
 else {
    return null;
  }
}
