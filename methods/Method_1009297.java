@Nullable private static List<String> down(@NotNull Call qualifier){
  List<String> nameList=null;
  if (qualifier.isCalling(KERNEL,__MODULE__,0)) {
    Call enclosingCall=enclosingModularMacroCall(qualifier);
    if (enclosingCall != null && enclosingCall instanceof StubBased) {
      StubBased enclosingStubBasedCall=(StubBased)enclosingCall;
      String canonicalName=enclosingStubBasedCall.canonicalName();
      if (canonicalName != null) {
        nameList=new ArrayList<String>();
        nameList.add(canonicalName);
      }
    }
  }
  return nameList;
}
