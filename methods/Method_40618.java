@NotNull public static String genLambdaName(){
  lambdaCounter=lambdaCounter + 1;
  return "lambda%" + lambdaCounter;
}
