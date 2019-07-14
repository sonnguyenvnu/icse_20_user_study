public List<String> addOperators(String num,int target){
  List<String> result=new ArrayList<String>();
  if (num == null || num.length() == 0) {
    return result;
  }
  helper(result,"",num,target,0,0,0);
  return result;
}
