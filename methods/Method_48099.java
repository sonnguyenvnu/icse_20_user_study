private void assureS3Bucket(){
  try {
    s3.createBucket(CreateBucketRequest.builder().createBucketConfiguration(CreateBucketConfiguration.builder().locationConstraint(s3BucketLocationConstraint).build()).bucket(s3Bucket).build());
    s3.putBucketTagging(PutBucketTaggingRequest.builder().bucket(s3Bucket).tagging(Tagging.builder().tagSet(tags).build()).build());
    log.info("Created bucket " + s3Bucket);
  }
 catch (  S3Exception e) {
    if (e.getErrorCode().equals("BucketAlreadyOwnedByYou")) {
      log.info("Bucket " + s3Bucket + " in region already owned by you");
    }
 else {
      throw new IllegalArgumentException("Unable to create/configure bucket",e);
    }
  }
}
