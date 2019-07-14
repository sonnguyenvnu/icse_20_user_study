/** 
 * Generate a spec method model which corresponds to a source error declaration like this: <pre><code> {@literal @}OnEvent(ErrorEvent.class) static void __internalOnErrorEvent(ComponentContext c, @FromEvent Exception exception) {} </code></pre> This is used to automatically generate this method stub when an <code>@OnError</code> declaration is used.
 */
public static SpecMethodModel<EventMethod,EventDeclarationModel> generateErrorEventHandlerDefinition(){
  return SpecMethodModel.<EventMethod,EventDeclarationModel>builder().modifiers(ImmutableList.of(Modifier.STATIC)).name(EventCaseGenerator.INTERNAL_ON_ERROR_HANDLER_NAME).returnTypeSpec(new TypeSpec(TypeName.VOID)).typeVariables(ImmutableList.of()).methodParams(ImmutableList.of(new SimpleMethodParamModel(new TypeSpec(ClassNames.COMPONENT_CONTEXT),"c",ImmutableList.of(),ImmutableList.of(),null),new SimpleMethodParamModel(new TypeSpec(ClassName.bestGuess("java.lang.Exception")),"exception",ImmutableList.of((Annotation)() -> FromEvent.class),ImmutableList.of(),null))).typeModel(new EventDeclarationModel(ClassNames.ERROR_EVENT,TypeName.VOID,ImmutableList.of(new FieldModel(FieldSpec.builder(ClassName.bestGuess("java.lang.Exception"),"exception",Modifier.PUBLIC).build(),null)),null)).build();
}