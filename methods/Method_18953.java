public static ImmutableList<EventDeclarationModel> getEventDeclarations(Elements elements,TypeElement element,Class<?> annotationType,EnumSet<RunMode> runMode){
  final List<AnnotationValue> eventTypes=ProcessorUtils.getAnnotationParameter(elements,element,annotationType,"events",List.class);
  final List<EventDeclarationModel> eventDeclarations;
  if (eventTypes != null) {
    eventDeclarations=new ArrayList<>();
    for (    AnnotationValue eventType : eventTypes) {
      final DeclaredType type=(DeclaredType)eventType.getValue();
      final TypeName returnType=runMode.contains(RunMode.ABI) ? TypeName.VOID : getReturnType(elements,type.asElement());
      final ImmutableList<FieldModel> fields=runMode.contains(RunMode.ABI) ? ImmutableList.of() : FieldsExtractor.extractFields(type.asElement());
      eventDeclarations.add(new EventDeclarationModel(ClassName.bestGuess(type.asElement().toString()),returnType,fields,type.asElement()));
    }
  }
 else {
    eventDeclarations=Collections.emptyList();
  }
  return ImmutableList.copyOf(eventDeclarations);
}
