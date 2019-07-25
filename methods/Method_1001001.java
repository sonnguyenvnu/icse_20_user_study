/** 
 * @since 2.7
 */
public static SimpleBeanPropertyDefinition construct(MapperConfig<?> config,AnnotatedMember member,PropertyName name,PropertyMetadata metadata,JsonInclude.Value inclusion){
  return new SimpleBeanPropertyDefinition(config.getAnnotationIntrospector(),member,name,metadata,inclusion);
}
