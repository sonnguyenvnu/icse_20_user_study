@Override public void move(String sourceBlobName,String targetBlobName) throws IOException {
  store.execute((Operation<Void>)fileContext -> {
    fileContext.rename(new Path(path,sourceBlobName),new Path(path,targetBlobName));
    return null;
  }
);
}
