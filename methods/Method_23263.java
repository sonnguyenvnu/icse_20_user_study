static public String[] subset(String[] list,int start,int count){
  String[] output=new String[count];
  System.arraycopy(list,start,output,0,count);
  return output;
}
