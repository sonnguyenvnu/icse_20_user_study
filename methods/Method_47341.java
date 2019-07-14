public static ArrayList<Boolean[]> parse(String permLine){
  ArrayList<Boolean[]> arrayList=new ArrayList<>(3);
  Boolean[] read=new Boolean[]{permLine.charAt(1) == 'r',permLine.charAt(4) == 'r',permLine.charAt(7) == 'r'};
  Boolean[] write=new Boolean[]{permLine.charAt(2) == 'w',permLine.charAt(5) == 'w',permLine.charAt(8) == 'w'};
  Boolean[] execute=new Boolean[]{permLine.charAt(3) == 'x',permLine.charAt(6) == 'x',permLine.charAt(9) == 'x'};
  arrayList.add(read);
  arrayList.add(write);
  arrayList.add(execute);
  return arrayList;
}
