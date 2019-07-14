private static String generateSummary(String methodName,String constantOutput){
  return "The arguments to the " + methodName + " method are the same object, so it always " + constantOutput + ". Please change the arguments to point to different objects or " + "consider using EqualsTester.";
}
