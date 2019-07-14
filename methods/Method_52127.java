private boolean isOK(String image){
  return "serialVersionUID".equals(image) || "serialPersistentFields".equals(image) || "IDENT".equals(image);
}
