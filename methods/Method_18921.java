/** 
 * Generate synthetic event methods if necessary based on delegate implementations. 
 */
private static ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> getCombinedEventMethods(ImmutableList<SpecMethodModel<DelegateMethod,Void>> delegateMethods,ImmutableList<SpecMethodModel<EventMethod,EventDeclarationModel>> eventMethods){
  final List<SpecMethodModel<EventMethod,EventDeclarationModel>> list=new ArrayList<>(eventMethods);
  if (ErrorEventHandlerGenerator.hasOnErrorDelegateMethod(delegateMethods)) {
    list.add(ErrorEventHandlerGenerator.generateErrorEventHandlerDefinition());
  }
  return ImmutableList.copyOf(list);
}
