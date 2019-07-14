static private boolean matchField(String field,String typed){
  return (field != null) && removeAccents(field.toLowerCase()).matches(typed);
}
