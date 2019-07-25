@Override public void move(String sourceBlobName,String targetBlobName) throws IOException {
  try {
    CopyObjectRequest request=new CopyObjectRequest(blobStore.bucket(),buildKey(sourceBlobName),blobStore.bucket(),buildKey(targetBlobName));
    if (blobStore.serverSideEncryption()) {
      ObjectMetadata objectMetadata=new ObjectMetadata();
      objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
      request.setNewObjectMetadata(objectMetadata);
    }
    SocketAccess.doPrivilegedVoid(() -> {
      blobStore.client().copyObject(request);
      blobStore.client().deleteObject(blobStore.bucket(),buildKey(sourceBlobName));
    }
);
  }
 catch (  AmazonS3Exception e) {
    throw new IOException(e);
  }
}
