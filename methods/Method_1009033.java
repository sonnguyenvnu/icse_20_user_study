public static void visit(WordprocessingMLPackage wmlPackage,boolean bodyOnly,Callback callback){
  MainDocumentPart mainDocument=null;
  RelationshipsPart relPart=null;
  List<Relationship> relList=null;
  List<Object> elementList=null;
  if ((wmlPackage != null) && (callback != null)) {
    mainDocument=wmlPackage.getMainDocumentPart();
    callback.walkJAXBElements(mainDocument.getJaxbElement().getBody());
    if (!bodyOnly) {
      relPart=mainDocument.getRelationshipsPart();
      relList=relPart.getRelationships().getRelationship();
      for (      Relationship rs : relList) {
        elementList=null;
        if (Namespaces.HEADER.equals(rs.getType())) {
          elementList=((HeaderPart)relPart.getPart(rs)).getJaxbElement().getContent();
        }
 else         if (Namespaces.FOOTER.equals(rs.getType())) {
          elementList=((FooterPart)relPart.getPart(rs)).getJaxbElement().getContent();
        }
 else         if (Namespaces.ENDNOTES.equals(rs.getType())) {
          elementList=new ArrayList();
          elementList.addAll(((EndnotesPart)relPart.getPart(rs)).getJaxbElement().getEndnote());
        }
 else         if (Namespaces.FOOTNOTES.equals(rs.getType())) {
          elementList=new ArrayList();
          elementList.addAll(((FootnotesPart)relPart.getPart(rs)).getJaxbElement().getFootnote());
        }
 else         if (Namespaces.COMMENTS.equals(rs.getType())) {
          elementList=new ArrayList();
          for (          Comment comment : ((CommentsPart)relPart.getPart(rs)).getJaxbElement().getComment()) {
            elementList.addAll(comment.getEGBlockLevelElts());
          }
        }
        if ((elementList != null) && (!elementList.isEmpty())) {
          log.debug("Processing target: " + rs.getTarget() + ", type: " + rs.getType());
          callback.walkJAXBElements(elementList);
        }
      }
    }
  }
}
