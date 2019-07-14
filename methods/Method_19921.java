public static void setStickers(final Context context,FirebaseAppIndex firebaseAppIndex){
  try {
    List<Indexable> stickers=getIndexableStickers(context);
    Indexable stickerPack=getIndexableStickerPack(context);
    List<Indexable> indexables=new ArrayList<>(stickers);
    indexables.add(stickerPack);
    Task<Void> task=firebaseAppIndex.update(indexables.toArray(new Indexable[indexables.size()]));
    task.addOnSuccessListener(new OnSuccessListener<Void>(){
      @Override public void onSuccess(      Void aVoid){
        Toast.makeText(context,"Successfully added stickers",Toast.LENGTH_SHORT).show();
      }
    }
);
    task.addOnFailureListener(new OnFailureListener(){
      @Override public void onFailure(      @NonNull Exception e){
        Log.d(TAG,FAILED_TO_INSTALL_STICKERS,e);
        Toast.makeText(context,FAILED_TO_INSTALL_STICKERS,Toast.LENGTH_SHORT).show();
      }
    }
);
  }
 catch (  IOException|FirebaseAppIndexingInvalidArgumentException e) {
    Log.e(TAG,"Unable to set stickers",e);
  }
}
