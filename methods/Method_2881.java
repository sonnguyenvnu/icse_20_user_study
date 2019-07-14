public void save(DataOutputStream out) throws Exception {
  out.writeInt(idToLabelMap.length);
  for (  String value : idToLabelMap) {
    TextUtility.writeString(value,out);
  }
}
