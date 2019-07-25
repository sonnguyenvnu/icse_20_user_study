@Override public void transform(Context context,Collection<TransformInput> inputs,Collection<TransformInput> referencedInputs,TransformOutputProvider outputProvider,boolean isIncremental) throws IOException, TransformException, InterruptedException {
  okHttpHunterExtension=(OkHttpHunterExtension)project.getExtensions().getByName("okHttpHunterExt");
  this.bytecodeWeaver.setExtension(okHttpHunterExtension);
  super.transform(context,inputs,referencedInputs,outputProvider,isIncremental);
}
