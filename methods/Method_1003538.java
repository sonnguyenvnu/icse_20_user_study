/** 
 * Initialize the Mapping.
 * @param method the source method that the mapping belongs to
 * @param messager the messager that can be used for outputting messages
 * @param typeFactory the type factory
 * @param accessorNaming the accessor naming utils
 * @param isReverse whether the init is for a reverse mapping
 * @param reverseSourceParameter the source parameter from the revers mapping
 */
private void init(SourceMethod method,FormattingMessager messager,TypeFactory typeFactory,AccessorNamingUtils accessorNaming,boolean isReverse,Parameter reverseSourceParameter){
  if (!method.isEnumMapping()) {
    sourceReference=new SourceReference.BuilderFromMapping().mapping(this).method(method).messager(messager).typeFactory(typeFactory).build();
    targetReference=new TargetReference.BuilderFromTargetMapping().mapping(this).isReverse(isReverse).method(method).messager(messager).typeFactory(typeFactory).accessorNaming(accessorNaming).reverseSourceParameter(reverseSourceParameter).build();
  }
}
