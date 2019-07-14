/** 
 * ?????????????
 * @param number
 * @return
 */
public boolean isHappy(int number){
  Set<Integer> set=new HashSet<>(30);
  while (number != 1) {
    int sum=0;
    while (number > 0) {
      sum+=(number % 10) * (number % 10);
      number=number / 10;
    }
    if (set.contains(sum)) {
      return false;
    }
 else {
      set.add(sum);
    }
    number=sum;
  }
  return true;
}
