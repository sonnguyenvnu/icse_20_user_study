/** 
 * Creates a new  {@link AnnotationWriter} using named values.
 * @param symbolTable where the constants used in this AnnotationWriter must be stored.
 * @param typeRef a reference to the annotated type. The sort of this type reference must be{@link TypeReference#CLASS_TYPE_PARAMETER},  {@link TypeReference#CLASS_TYPE_PARAMETER_BOUND} or {@link TypeReference#CLASS_EXTENDS}. See {@link TypeReference}.
 * @param typePath the path to the annotated type argument, wildcard bound, array element type, orstatic inner type within 'typeRef'. May be  {@literal null} if the annotation targets'typeRef' as a whole.
 * @param descriptor the class descriptor of the annotation class.
 * @param previousAnnotation the previously visited annotation of theRuntime[In]Visible[Type]Annotations attribute to which this annotation belongs, or null in other cases (e.g. nested or array annotations).
 */
static AnnotationWriter create(final SymbolTable symbolTable,final int typeRef,final TypePath typePath,final String descriptor,final AnnotationWriter previousAnnotation){
  ByteVector typeAnnotation=new ByteVector();
  TypeReference.putTarget(typeRef,typeAnnotation);
  TypePath.put(typePath,typeAnnotation);
  typeAnnotation.putShort(symbolTable.addConstantUtf8(descriptor)).putShort(0);
  return new AnnotationWriter(symbolTable,true,typeAnnotation,previousAnnotation);
}
