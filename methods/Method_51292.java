private static void sortFiles(final PMDConfiguration configuration,final List<DataSource> files){
  if (configuration.isStressTest()) {
    Collections.shuffle(files);
  }
 else {
    final boolean useShortNames=configuration.isReportShortNames();
    final String inputPaths=configuration.getInputPaths();
    Collections.sort(files,new Comparator<DataSource>(){
      @Override public int compare(      DataSource left,      DataSource right){
        String leftString=left.getNiceFileName(useShortNames,inputPaths);
        String rightString=right.getNiceFileName(useShortNames,inputPaths);
        return leftString.compareTo(rightString);
      }
    }
);
  }
}
