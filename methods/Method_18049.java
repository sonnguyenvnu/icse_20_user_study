Collection<ValueNode> getAllInputs(){
  if (mInputs == null) {
    return Collections.emptySet();
  }
  return mInputs.values();
}
