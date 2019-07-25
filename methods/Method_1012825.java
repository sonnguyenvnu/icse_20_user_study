private int[] parse(String[] elements){
  int[] out=new int[elements.length];
  for (int i=0; i < elements.length; i++) {
    try {
      out[i]=Integer.parseInt(elements[i]);
    }
 catch (    NumberFormatException e) {
      out[i]=0;
    }
  }
  return out;
}
