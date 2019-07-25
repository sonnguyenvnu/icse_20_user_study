/** 
 * Initialize the state of this request with defaults defined in the {@link InitializrMetadata metadata}.
 * @param metadata the metadata to use
 */
public void initialize(InitializrMetadata metadata){
  BeanWrapperImpl bean=new BeanWrapperImpl(this);
  metadata.defaults().forEach((key,value) -> {
    if (bean.isWritableProperty(key)) {
      if (!key.equals("packageName")) {
        bean.setPropertyValue(key,value);
      }
    }
  }
);
}
