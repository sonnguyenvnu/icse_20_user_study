String dump(){
  String s=super.dump();
  s+=" " + CustomLogicModel.escape(modelName);
  int i;
  for (i=0; i != getPostCount(); i++) {
    if (pins[i].output)     s+=" " + volts[i];
  }
  return s;
}
