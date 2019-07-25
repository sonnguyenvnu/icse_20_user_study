public static void reverter(String inputfilepath,String instancePath) throws Docx4JException {
  WordprocessingMLPackage instancePkg=WordprocessingMLPackage.load(new java.io.File(instancePath));
  OpenDoPEReverter reverter=new OpenDoPEReverter(WordprocessingMLPackage.load(new java.io.File(inputfilepath)),instancePkg);
  System.out.println("reverted? " + reverter.revert());
  SaveToZipFile saver=new SaveToZipFile(instancePkg);
  saver.save(filepathprefix + "_reverted.docx");
  System.out.println("Saved: " + filepathprefix + "_reverted.docx");
}
