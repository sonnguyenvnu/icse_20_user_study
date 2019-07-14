@UiThread @NonNull private static List<ObjectAnimator> getBeats(@NonNull View view){
  ObjectAnimator[] animator=new ObjectAnimator[]{ObjectAnimator.ofFloat(view,"scaleY",1,1.1f,1),ObjectAnimator.ofFloat(view,"scaleX",1,1.1f,1)};
  return Arrays.asList(animator);
}
