/** 
 * Reinit fields so this pkg object can be re-used.
 * @since 3.3.7
 */
@Override public void reset(){
  super.reset();
  handled=new HashMap<String,String>();
  parts=new Parts();
  externalResources=new HashMap<ExternalTarget,Part>();
  customXmlDataStorageParts=new HashMap<String,CustomXmlPart>();
  sourcePartStore=null;
  targetPartStore=null;
  docPropsCorePart=null;
  docPropsExtendedPart=null;
  docPropsCustomPart=null;
  name=null;
  log.info("reset complete");
}
