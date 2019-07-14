public void segment(String text,String normalized,List<String> output){
  if (text.isEmpty())   return;
  Instance instance=new CWSInstance(normalized,model.featureMap);
  segment(text,instance,output);
}
