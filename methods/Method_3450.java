public static void printNERScore(Map<String,double[]> scores){
  System.out.printf("%4s\t%6s\t%6s\t%6s\n","NER","P","R","F1");
  for (  Map.Entry<String,double[]> entry : scores.entrySet()) {
    String type=entry.getKey();
    double[] s=entry.getValue();
    System.out.printf("%4s\t%6.2f\t%6.2f\t%6.2f\n",type,s[0],s[1],s[2]);
  }
}
