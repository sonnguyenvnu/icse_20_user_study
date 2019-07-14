private void replaceImage(RetainingDataSourceSupplier<CloseableReference<CloseableImage>> retainingSupplier){
  retainingSupplier.replaceSupplier(Fresco.getImagePipeline().getDataSourceSupplier(ImageRequest.fromUri(getNextUri()),null,ImageRequest.RequestLevel.FULL_FETCH));
}
