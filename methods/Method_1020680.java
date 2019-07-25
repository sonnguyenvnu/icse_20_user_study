public void modify(ObjectMapper mapper){
  AnnotationIntrospector jongoIntrospector=new JongoAnnotationIntrospector();
  AnnotationIntrospector defaultIntrospector=mapper.getSerializationConfig().getAnnotationIntrospector();
  AnnotationIntrospector pair=new AnnotationIntrospectorPair(jongoIntrospector,defaultIntrospector);
  mapper.setAnnotationIntrospector(pair);
}
