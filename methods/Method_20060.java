/** 
 * Classifies a frame from the preview stream.
 */
Task<List<String>> classifyFrame(ByteBuffer buffer,int width,int height) throws FirebaseMLException {
  if (interpreter == null) {
    Log.e(TAG,"Image classifier has not been initialized; Skipped.");
    List<String> uninitialized=new ArrayList<>();
    uninitialized.add("Uninitialized Classifier.");
    Tasks.forResult(uninitialized);
  }
  ByteBuffer imgData=convertBitmapToByteBuffer(buffer,width,height);
  FirebaseModelInputs inputs=new FirebaseModelInputs.Builder().add(imgData).build();
  return interpreter.run(inputs,dataOptions).addOnFailureListener(new OnFailureListener(){
    @Override public void onFailure(    @NonNull Exception e){
      Log.e(TAG,"Failed to get labels array: " + e.getMessage());
      e.printStackTrace();
    }
  }
).continueWith(new Continuation<FirebaseModelOutputs,List<String>>(){
    @Override public List<String> then(    Task<FirebaseModelOutputs> task) throws Exception {
      if (mUseQuantizedModel) {
        byte[][] labelProbArray=task.getResult().<byte[][]>getOutput(0);
        return getTopLabels(labelProbArray);
      }
 else {
        float[][] labelProbArray=task.getResult().<float[][]>getOutput(0);
        return getTopLabels(labelProbArray);
      }
    }
  }
);
}
