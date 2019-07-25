public static void crosspost(Submission submission,Activity mContext){
  Crosspost.toCrosspost=submission;
  mContext.startActivity(new Intent(mContext,Crosspost.class));
}
