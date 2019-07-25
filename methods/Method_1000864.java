public static CreatorCandidate construct(AnnotationIntrospector intr,AnnotatedWithParams creator,BeanPropertyDefinition[] propDefs){
  final int pcount=creator.getParameterCount();
  Param[] params=new Param[pcount];
  for (int i=0; i < pcount; ++i) {
    AnnotatedParameter annParam=creator.getParameter(i);
    JacksonInject.Value injectId=intr.findInjectableValue(annParam);
    params[i]=new Param(annParam,(propDefs == null) ? null : propDefs[i],injectId);
  }
  return new CreatorCandidate(intr,creator,params,pcount);
}
