@Bean public SparklrServiceImpl sparklrService(@Value("${sparklrPhotoListURL}") String sparklrPhotoListURL,@Value("${sparklrPhotoURLPattern}") String sparklrPhotoURLPattern,@Value("${sparklrTrustedMessageURL}") String sparklrTrustedMessageURL,@Qualifier("sparklrRestTemplate") RestOperations sparklrRestTemplate,@Qualifier("trustedClientRestTemplate") RestOperations trustedClientRestTemplate){
  SparklrServiceImpl sparklrService=new SparklrServiceImpl();
  sparklrService.setSparklrPhotoListURL(sparklrPhotoListURL);
  sparklrService.setSparklrPhotoURLPattern(sparklrPhotoURLPattern);
  sparklrService.setSparklrTrustedMessageURL(sparklrTrustedMessageURL);
  sparklrService.setSparklrRestTemplate(sparklrRestTemplate);
  sparklrService.setTrustedClientRestTemplate(trustedClientRestTemplate);
  return sparklrService;
}
