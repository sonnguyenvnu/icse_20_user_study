private static List<String> getPositionsFromDataset(Integer[] colors){
  final List<String> positions=new ArrayList<>();
  for (int i=0; i < colors.length; i++) {
    positions.add(i,Integer.toString(i));
  }
  return positions;
}
