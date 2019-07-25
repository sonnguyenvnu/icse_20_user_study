@Override public <D extends Device>D describe(D undescribedDevice,String descriptorXml) throws DescriptorBindingException, ValidationException {
  D device=null;
  DescriptorBindingException originalException;
  try {
    try {
      if (descriptorXml != null)       descriptorXml=descriptorXml.trim();
      device=super.describe(undescribedDevice,descriptorXml);
      return device;
    }
 catch (    DescriptorBindingException ex) {
      log.warning("Regular parsing failed: " + Exceptions.unwrap(ex).getMessage());
      originalException=ex;
    }
    String fixedXml;
    fixedXml=fixGarbageLeadingChars(descriptorXml);
    if (fixedXml != null) {
      try {
        device=super.describe(undescribedDevice,fixedXml);
        return device;
      }
 catch (      DescriptorBindingException ex) {
        log.warning("Removing leading garbage didn't work: " + Exceptions.unwrap(ex).getMessage());
      }
    }
    fixedXml=fixGarbageTrailingChars(descriptorXml,originalException);
    if (fixedXml != null) {
      try {
        device=super.describe(undescribedDevice,fixedXml);
        return device;
      }
 catch (      DescriptorBindingException ex) {
        log.warning("Removing trailing garbage didn't work: " + Exceptions.unwrap(ex).getMessage());
      }
    }
    DescriptorBindingException lastException=originalException;
    fixedXml=descriptorXml;
    for (int retryCount=0; retryCount < 5; retryCount++) {
      fixedXml=fixMissingNamespaces(fixedXml,lastException);
      if (fixedXml != null) {
        try {
          device=super.describe(undescribedDevice,fixedXml);
          return device;
        }
 catch (        DescriptorBindingException ex) {
          log.warning("Fixing namespace prefix didn't work: " + Exceptions.unwrap(ex).getMessage());
          lastException=ex;
        }
      }
 else {
        break;
      }
    }
    fixedXml=XmlPullParserUtils.fixXMLEntities(descriptorXml);
    if (!fixedXml.equals(descriptorXml)) {
      try {
        device=super.describe(undescribedDevice,fixedXml);
        return device;
      }
 catch (      DescriptorBindingException ex) {
        log.warning("Fixing XML entities didn't work: " + Exceptions.unwrap(ex).getMessage());
      }
    }
    handleInvalidDescriptor(descriptorXml,originalException);
  }
 catch (  ValidationException ex) {
    device=handleInvalidDevice(descriptorXml,device,ex);
    if (device != null)     return device;
  }
  throw new IllegalStateException("No device produced, did you swallow exceptions in your subclass?");
}
