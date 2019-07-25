private List<Fraction> generate(int l,int r,int[] nums){
  if (l > r) {
    return new ArrayList<>();
  }
 else   if (l == r) {
    return Arrays.asList(new Fraction(nums[l],1));
  }
 else {
    List<Fraction> result=new ArrayList<>();
    for (int i=l; i < r; i++) {
      for (int j=l; j <= i; j++) {
        List<Fraction> left=generate(l,j,nums);
        List<Fraction> right=generate(j + 1,r,nums);
        if (right.isEmpty()) {
          result.addAll(left);
        }
 else         if (left.isEmpty()) {
          result.addAll(right);
        }
 else {
          for (          Fraction lF : left) {
            for (            Fraction rF : right) {
              int n=(lF.n * rF.d + rF.n * lF.d);
              int d=(lF.d * rF.d);
              Fraction sum=new Fraction(n,d);
              n=(lF.n * rF.d - (rF.n * lF.d));
              d=(lF.d * rF.d);
              Fraction diff=new Fraction(n,d);
              n=(lF.n * rF.n);
              d=(lF.d * rF.d);
              Fraction prod=new Fraction(n,d);
              n=(lF.n * rF.d);
              d=(lF.d * rF.n);
              Fraction div=new Fraction(n,d);
              result.add(sum);
              result.add(diff);
              result.add(prod);
              result.add(div);
            }
          }
        }
      }
    }
    return result;
  }
}
