@Override public boolean isIncidentOn(Vertex vertex){
  for (int i=0; i < getArity(); i++) {
    if (it().getVertex(i).equals(vertex)) {
      return true;
    }
  }
  return false;
}
