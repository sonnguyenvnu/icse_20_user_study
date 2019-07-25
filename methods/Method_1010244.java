public static void remove(SNode node,SProperty property){
  if (node != null) {
    SNodeAccessUtil.setPropertyValue(node,property,null);
  }
}
