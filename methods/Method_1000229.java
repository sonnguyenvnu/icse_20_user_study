public boolean makesquare(int[] nums){
  if (nums.length == 0)   return false;
  int sum=0;
  for (  int n : nums) {
    sum+=n;
  }
  int side=sum / 4;
  if ((sum % 4) != 0)   return false;
  List<List<Pair>> list=powerSet(nums,side);
  Set<Integer> hashIndex=new HashSet<>();
  int cons=0;
  for (int i=0; i < nums.length; i++) {
    cons|=(1 << i);
  }
  for (int i=0; i < list.size(); i++) {
    for (int j=i + 1; j < list.size(); j++) {
      Set<Integer> indexList=new HashSet<>();
      List<Pair> list1=list.get(i);
      List<Pair> list2=list.get(j);
      int hash=0;
      for (      Pair l1 : list1) {
        indexList.add(l1.i);
        hash|=(1 << l1.i);
      }
      boolean allUnique=true;
      for (      Pair l2 : list2) {
        if (indexList.contains(l2.i)) {
          allUnique=false;
          break;
        }
        indexList.add(l2.i);
        hash|=(1 << l2.i);
      }
      if (allUnique) {
        hashIndex.add(hash);
        int complement=((~hash) & cons);
        if (hashIndex.contains(complement))         return true;
      }
    }
  }
  return false;
}
