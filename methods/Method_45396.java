protected final Predicate<Field> isClassField(){
  return new Predicate<Field>(){
    @Override public boolean apply(    final Field field){
      return "class".equals(field.getName());
    }
  }
;
}
