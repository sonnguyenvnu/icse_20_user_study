public static SMILDocument createSmilDocument(PduBody pb){
  SMILDocument document=new SmilDocumentImpl();
  SMILElement smil=(SMILElement)document.createElement("smil");
  smil.setAttribute("xmlns","http://www.w3.org/2001/SMIL20/Language");
  document.appendChild(smil);
  SMILElement head=(SMILElement)document.createElement("head");
  smil.appendChild(head);
  SMILLayoutElement layout=(SMILLayoutElement)document.createElement("layout");
  head.appendChild(layout);
  SMILElement body=(SMILElement)document.createElement("body");
  smil.appendChild(body);
  SMILParElement par=addPar(document);
  int partsNum=pb.getPartsNum();
  if (partsNum == 0) {
    return document;
  }
  boolean hasText=false;
  boolean hasMedia=false;
  for (int i=0; i < partsNum; i++) {
    if ((par == null) || (hasMedia && hasText)) {
      par=addPar(document);
      hasText=false;
      hasMedia=false;
    }
    PduPart part=pb.getPart(i);
    String contentType=new String(part.getContentType());
    if (contentType.equals(ContentType.TEXT_PLAIN) || contentType.equalsIgnoreCase(ContentType.APP_WAP_XHTML) || contentType.equals(ContentType.TEXT_HTML)) {
      SMILMediaElement textElement=createMediaElement(ELEMENT_TAG_TEXT,document,part.generateLocation());
      par.appendChild(textElement);
      hasText=true;
    }
 else     if (ContentType.isImageType(contentType)) {
      SMILMediaElement imageElement=createMediaElement(ELEMENT_TAG_IMAGE,document,part.generateLocation());
      par.appendChild(imageElement);
      hasMedia=true;
    }
 else     if (ContentType.isVideoType(contentType)) {
      SMILMediaElement videoElement=createMediaElement(ELEMENT_TAG_VIDEO,document,part.generateLocation());
      par.appendChild(videoElement);
      hasMedia=true;
    }
 else     if (ContentType.isAudioType(contentType)) {
      SMILMediaElement audioElement=createMediaElement(ELEMENT_TAG_AUDIO,document,part.generateLocation());
      par.appendChild(audioElement);
      hasMedia=true;
    }
 else     if (contentType.equals(ContentType.TEXT_VCARD)) {
      SMILMediaElement textElement=createMediaElement(ELEMENT_TAG_VCARD,document,part.generateLocation());
      par.appendChild(textElement);
      hasMedia=true;
    }
 else {
      Timber.e("creating_smil_document","unknown mimetype");
    }
  }
  return document;
}
