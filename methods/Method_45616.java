protected void readLine(URL url,String line){
  String[] aliasAndClassName=parseAliasAndClassName(line);
  if (aliasAndClassName == null || aliasAndClassName.length != 2) {
    return;
  }
  String alias=aliasAndClassName[0];
  String className=aliasAndClassName[1];
  Class tmp;
  try {
    tmp=ClassUtils.forName(className,false);
  }
 catch (  Throwable e) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("Extension {} of extensible {} is disabled, cause by: {}",className,interfaceName,ExceptionUtils.toShortString(e,2));
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Extension " + className + " of extensible " + interfaceName + " is disabled.",e);
    }
    return;
  }
  if (!interfaceClass.isAssignableFrom(tmp)) {
    throw new IllegalArgumentException("Error when load extension of extensible " + interfaceName + " from file:" + url + ", " + className + " is not subtype of interface.");
  }
  Class<? extends T> implClass=(Class<? extends T>)tmp;
  Extension extension=implClass.getAnnotation(Extension.class);
  if (extension == null) {
    throw new IllegalArgumentException("Error when load extension of extensible " + interfaceName + " from file:" + url + ", " + className + " must add annotation @Extension.");
  }
 else {
    String aliasInCode=extension.value();
    if (StringUtils.isBlank(aliasInCode)) {
      throw new IllegalArgumentException("Error when load extension of extensible " + interfaceClass + " from file:" + url + ", " + className + "'s alias of @Extension is blank");
    }
    if (alias == null) {
      alias=aliasInCode;
    }
 else {
      if (!aliasInCode.equals(alias)) {
        throw new IllegalArgumentException("Error when load extension of extensible " + interfaceName + " from file:" + url + ", aliases of " + className + " are " + "not equal between " + aliasInCode + "(code) and " + alias + "(file).");
      }
    }
    if (extensible.coded() && extension.code() < 0) {
      throw new IllegalArgumentException("Error when load extension of extensible " + interfaceName + " from file:" + url + ", code of @Extension must >=0 at " + className + ".");
    }
  }
  if (StringUtils.DEFAULT.equals(alias) || StringUtils.ALL.equals(alias)) {
    throw new IllegalArgumentException("Error when load extension of extensible " + interfaceName + " from file:" + url + ", alias of @Extension must not \"default\" and \"*\" at " + className + ".");
  }
  ExtensionClass old=all.get(alias);
  ExtensionClass<T> extensionClass=null;
  if (old != null) {
    if (extension.override()) {
      if (extension.order() < old.getOrder()) {
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("Extension of extensible {} with alias {} override from {} to {} failure, " + "cause by: order of old extension is higher",interfaceName,alias,old.getClazz(),implClass);
        }
      }
 else {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Extension of extensible {} with alias {}: {} has been override to {}",interfaceName,alias,old.getClazz(),implClass);
        }
        extensionClass=buildClass(extension,implClass,alias);
      }
    }
 else {
      if (old.isOverride() && old.getOrder() >= extension.order()) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Extension of extensible {} with alias {}: {} has been loaded, ignore origin {}",interfaceName,alias,old.getClazz(),implClass);
        }
      }
 else {
        throw new IllegalStateException("Error when load extension of extensible " + interfaceClass + " from file:" + url + ", Duplicate class with same alias: " + alias + ", " + old.getClazz() + " and " + implClass);
      }
    }
  }
 else {
    extensionClass=buildClass(extension,implClass,alias);
  }
  if (extensionClass != null) {
    for (    Map.Entry<String,ExtensionClass<T>> entry : all.entrySet()) {
      ExtensionClass existed=entry.getValue();
      if (extensionClass.getOrder() >= existed.getOrder()) {
        String[] rejection=extensionClass.getRejection();
        if (CommonUtils.isNotEmpty(rejection)) {
          for (          String rej : rejection) {
            existed=all.get(rej);
            if (existed == null || extensionClass.getOrder() < existed.getOrder()) {
              continue;
            }
            ExtensionClass removed=all.remove(rej);
            if (removed != null) {
              if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Extension of extensible {} with alias {}: {} has been reject by new {}",interfaceName,removed.getAlias(),removed.getClazz(),implClass);
              }
            }
          }
        }
      }
 else {
        String[] rejection=existed.getRejection();
        if (CommonUtils.isNotEmpty(rejection)) {
          for (          String rej : rejection) {
            if (rej.equals(extensionClass.getAlias())) {
              if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Extension of extensible {} with alias {}: {} has been reject by old {}",interfaceName,alias,implClass,existed.getClazz());
                return;
              }
            }
          }
        }
      }
    }
    loadSuccess(alias,extensionClass);
  }
}
