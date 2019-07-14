void setInput(String name,ValueNode input){
  if (mInputs == null) {
    mInputs=new LinkedHashMap<>();
  }
  mInputs.put(name,input);
}
