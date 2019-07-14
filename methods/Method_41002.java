public static String[] getItemDescriptions(){
  List<String> l=new ArrayList<String>(Arrays.asList(ITEM_DESCRIPTIONS));
  l.addAll(Arrays.asList(TriggerSupport.getItemDescriptions()));
  return l.toArray(new String[l.size()]);
}
