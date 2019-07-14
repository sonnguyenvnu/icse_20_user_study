protected void addFirstDatapoint(String name,int amountOfFiles,long totalBytes,boolean move){
  if (!getDataPackages().isEmpty()) {
    throw new IllegalStateException("This is not the first datapoint!");
  }
  DatapointParcelable intent1=new DatapointParcelable(name,amountOfFiles,totalBytes,move);
  putDataPackage(intent1);
}
