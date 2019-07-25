void unregister(ContextAssistant assistant){
  boolean wasRemoved=myContextAssistants.remove(assistant);
  assert wasRemoved : "trying to unregister not registered context assistant";
  if (assistant == myActiveAssistant) {
    hideMenu();
  }
}
