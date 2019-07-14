@Nullable ValueNode getInputUnsafe(String name){
  if (mInputs == null) {
    return null;
  }
  return mInputs.get(name);
}
