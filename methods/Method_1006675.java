@Override public ReadableOrderProductDownload populate(OrderProductDownload source,ReadableOrderProductDownload target,MerchantStore store,Language language) throws ConversionException {
  try {
    target.setProductName(source.getOrderProduct().getProductName());
    target.setDownloadCount(source.getDownloadCount());
    target.setDownloadExpiryDays(source.getMaxdays());
    target.setId(source.getId());
    target.setFileName(source.getOrderProductFilename());
    target.setOrderId(source.getOrderProduct().getOrder().getId());
    return target;
  }
 catch (  Exception e) {
    throw new ConversionException(e);
  }
}
