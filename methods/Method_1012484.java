String dump(){
  String s=super.dump() + " " + inductance + " " + couplingCoef + " " + CustomLogicModel.escape(description) + " " + coilCount + " ";
  int i;
  for (i=0; i != coilCount; i++) {
    s+=coilCurrents[i] + " ";
  }
  return s;
}
