@JsonIgnore public List<String> getValidField(){
  return Arrays.stream(this.getClass().getDeclaredFields()).map(field -> {
    try {
      if (!ObjectUtils.isEmpty(field.get(this))) {
        return field.getName();
      }
      return null;
    }
 catch (    IllegalAccessException e) {
    }
    return null;
  }
).filter(Objects::nonNull).collect(Collectors.toList());
}
