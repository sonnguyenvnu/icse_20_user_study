public List<Integer> findDisappearedNumbers(int[] nums){
  List<Integer> result=new ArrayList<Integer>();
  HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
  for (int i=1; i <= nums.length; i++) {
    map.put(i,1);
  }
  for (int i=0; i < nums.length; i++) {
    if (map.containsKey(nums[i])) {
      map.put(nums[i],-1);
    }
  }
  for (  int i : map.keySet()) {
    if (map.get(i) != -1) {
      result.add(i);
    }
  }
  return result;
}
