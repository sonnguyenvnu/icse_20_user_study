public List<SubstituteAction> collect(){
  ArrayList<SubstituteAction> outItems=new ArrayList<>();
  collectItems(myMenuItems,outItems);
  return outItems;
}
