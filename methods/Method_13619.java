@JsonIgnore public AbstractDataSourceProperties getValidDataSourceProperties(){
  List<String> invalidFields=getValidField();
  if (invalidFields.size() == 1) {
    try {
      this.getClass().getDeclaredField(invalidFields.get(0)).setAccessible(true);
      return (AbstractDataSourceProperties)this.getClass().getDeclaredField(invalidFields.get(0)).get(this);
    }
 catch (    IllegalAccessException e) {
    }
catch (    NoSuchFieldException e) {
    }
  }
  return null;
}
