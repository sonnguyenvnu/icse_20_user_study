private String removerOrSetter(ListenerClass listenerClass,boolean requiresRemoval){
  return requiresRemoval ? listenerClass.remover() : listenerClass.setter();
}
