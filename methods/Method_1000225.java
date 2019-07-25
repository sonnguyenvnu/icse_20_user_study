public int flipgame(int[] fronts,int[] backs){
  List<Integer> numbers=new ArrayList<>();
  for (int i=0; i < fronts.length; i++) {
    numbers.add(fronts[i]);
    numbers.add(backs[i]);
  }
  Collections.sort(numbers);
  for (  int n : numbers) {
    boolean success=true;
    for (int i=0; i < fronts.length; i++) {
      if (n == fronts[i] && n == backs[i]) {
        success=false;
        break;
      }
    }
    if (success) {
      return n;
    }
  }
  return 0;
}
