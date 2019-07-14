/** 
 * Creates a new  {@link AnnotationWriter} using named values.
 * @param symbolTable where the constants used in this AnnotationWriter must be stored.
 * @param descriptor the class descriptor of the annotation class.
 * @param previousAnnotation the previously visited annotation of theRuntime[In]Visible[Type]Annotations attribute to which this annotation belongs, or null in other cases (e.g. nested or array annotations).
 */
static AnnotationWriter create(final SymbolTable symbolTable,final String descriptor,final AnnotationWriter previousAnnotation){
  ByteVector annotation=new ByteVector();
  annotation.putShort(symbolTable.addConstantUtf8(descriptor)).putShort(0);
  return new AnnotationWriter(symbolTable,true,annotation,previousAnnotation);
}
