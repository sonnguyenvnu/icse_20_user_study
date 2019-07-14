public static void clearStickers(final Context context,FirebaseAppIndex firebaseAppIndex){
  Task<Void> task=firebaseAppIndex.removeAll();
  task.addOnSuccessListener(new OnSuccessListener<Void>(){
    @Override public void onSuccess(    Void aVoid){
      Toast.makeText(context,"Successfully cleared stickers",Toast.LENGTH_SHORT).show();
    }
  }
);
  task.addOnFailureListener(new OnFailureListener(){
    @Override public void onFailure(    @NonNull Exception e){
      Log.w(TAG,FAILED_TO_CLEAR_STICKERS,e);
      Toast.makeText(context,FAILED_TO_CLEAR_STICKERS,Toast.LENGTH_SHORT).show();
    }
  }
);
}
