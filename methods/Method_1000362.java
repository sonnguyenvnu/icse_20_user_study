public static FieldMatcher simple(String... fields){
  final Set<String> m=new HashSet<String>(Arrays.asList(fields));
  return new FieldMatcher(){
    public boolean match(    String str){
      return m.contains(str);
    }
    public boolean match(    MappingField mf,    Object obj){
      return this.match(mf.getName());
    }
  }
;
}
