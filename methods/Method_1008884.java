/** 
 * {@inheritDoc} 
 */
protected void read() throws IOException {
  AFMFile afm=null;
  PFMFile pfm=null;
  InputStream afmIn=null;
  for (int i=0; i < AFM_EXTENSIONS.length; i++) {
    try {
      String afmUri=this.fontFileURI.substring(0,this.fontFileURI.length() - 4) + AFM_EXTENSIONS[i];
      afmIn=openFontUri(resolver,afmUri);
      if (afmIn != null) {
        break;
      }
    }
 catch (    IOException ioe) {
    }
  }
  if (afmIn != null) {
    try {
      AFMParser afmParser=new AFMParser();
      afm=afmParser.parse(afmIn);
    }
  finally {
      IOUtils.closeQuietly(afmIn);
    }
  }
  String pfmUri=getPFMURI(this.fontFileURI);
  InputStream pfmIn=null;
  try {
    pfmIn=openFontUri(resolver,pfmUri);
  }
 catch (  IOException ioe) {
  }
  if (pfmIn != null) {
    try {
      pfm=new PFMFile();
      pfm.load(pfmIn);
    }
 catch (    IOException ioe) {
      if (afm == null) {
        throw ioe;
      }
    }
 finally {
      IOUtils.closeQuietly(pfmIn);
    }
  }
  if (afm == null && pfm == null) {
    throw new java.io.FileNotFoundException("Neither an AFM nor a PFM file was found for " + this.fontFileURI);
  }
  buildFont(afm,pfm);
  this.loaded=true;
}
