public static void substitute(double[] m,double oldValue,double newValue){
  for (int i=m.length - 1; i >= 0; i--)   if (m[i] == oldValue)   m[i]=newValue;
}
