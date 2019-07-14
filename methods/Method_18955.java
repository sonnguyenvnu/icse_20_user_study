/** 
 * Get the delegate methods from the given  {@link TypeElement}. 
 */
public static ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> getOnEventMethods(Elements elements,TypeElement typeElement,List<Class<? extends Annotation>> permittedInterStageInputAnnotations,Messager messager,EnumSet<RunMode> runMode){
  final List<SpecMethodModel<EventMethod,EventDeclarationModel>> delegateMethods=new ArrayList<>();
  for (  Element enclosedElement : typeElement.getEnclosedElements()) {
    if (enclosedElement.getKind() != ElementKind.METHOD) {
      continue;
    }
    final OnEvent onEventAnnotation=enclosedElement.getAnnotation(OnEvent.class);
    if (onEventAnnotation != null) {
      final ExecutableElement executableElement=(ExecutableElement)enclosedElement;
      final List<MethodParamModel> methodParams=getMethodParams(executableElement,messager,getPermittedMethodParamAnnotations(permittedInterStageInputAnnotations),permittedInterStageInputAnnotations,ImmutableList.<Class<? extends Annotation>>of());
      final DeclaredType eventClassDeclaredType=ProcessorUtils.getAnnotationParameter(elements,executableElement,OnEvent.class,"value",DeclaredType.class);
      final Element eventClass=eventClassDeclaredType.asElement();
      final TypeName returnType=runMode.contains(RunMode.ABI) ? TypeName.VOID : EventDeclarationsExtractor.getReturnType(elements,eventClass);
      final ImmutableList<FieldModel> fields=runMode.contains(RunMode.ABI) ? ImmutableList.of() : FieldsExtractor.extractFields(eventClass);
      final SpecMethodModel<EventMethod,EventDeclarationModel> eventMethod=SpecMethodModel.<EventMethod,EventDeclarationModel>builder().annotations(ImmutableList.of()).modifiers(ImmutableList.copyOf(new ArrayList<>(executableElement.getModifiers()))).name(executableElement.getSimpleName()).returnTypeSpec(generateTypeSpec(executableElement.getReturnType())).typeVariables(ImmutableList.copyOf(getTypeVariables(executableElement))).methodParams(ImmutableList.copyOf(methodParams)).representedObject(executableElement).typeModel(new EventDeclarationModel(ClassName.bestGuess(eventClass.toString()),returnType,fields,eventClass)).build();
      delegateMethods.add(eventMethod);
    }
  }
  return ImmutableList.copyOf(delegateMethods);
}
