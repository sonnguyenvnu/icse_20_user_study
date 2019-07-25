private static float average(ArrayList<Integer> costTimeList){
  if (costTimeList == null || costTimeList.size() == 0) {
    return 0;
  }
  int sum=0;
  for (  int val : costTimeList) {
    sum=sum + val;
  }
  return (float)sum / (float)costTimeList.size();
}
