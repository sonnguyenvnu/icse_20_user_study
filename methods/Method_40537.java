@Override protected void printType(CyclicTypeRecorder ctr,StringBuilder sb){
  if (getClassType().isClassType()) {
    sb.append(getClassType().asClassType().getName());
  }
}
