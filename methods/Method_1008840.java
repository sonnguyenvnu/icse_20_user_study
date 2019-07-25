@Override public List<Object> apply(Object o){
  if (o instanceof P) {
    currentP=createNode(document,NODE_BLOCK);
    currentSpan=null;
    if (tc.peek() != null) {
      tc.peek().appendChild(currentP);
    }
 else {
      parentNode.appendChild(currentP);
    }
    pPr=((P)o).getPPr();
    currentP=handlePPr(conversionContext,pPr,false,currentP);
  }
 else   if (o instanceof org.docx4j.wml.R) {
    if (!conversionContext.isInComplexFieldDefinition()) {
      Element spanEl=createNode(document,NODE_INLINE);
      currentSpan=spanEl;
      rPr=((R)o).getRPr();
      if (rPr != null) {
        handleRPr(conversionContext,pPr,rPr,currentSpan);
      }
      if (currentP == null) {
        parentNode.appendChild(spanEl);
      }
 else {
        rtlAwareAppendChildToCurrentP(spanEl);
      }
    }
  }
 else   if (o instanceof org.docx4j.wml.FldChar) {
    conversionContext.updateComplexFieldDefinition(((org.docx4j.wml.FldChar)o).getFldCharType());
  }
 else   if (o instanceof org.docx4j.wml.Text) {
    if (!conversionContext.isInComplexFieldDefinition()) {
      if (currentSpan == null) {
        log.error("null currentSpan! " + ((Text)o).getValue());
        Element spanEl=createNode(document,NODE_INLINE);
        if (currentP == null) {
          parentNode.appendChild(spanEl);
        }
 else {
          currentP.appendChild(spanEl);
        }
        currentSpan=spanEl;
      }
      log.debug(((Text)o).getValue());
      DocumentFragment df=(DocumentFragment)conversionContext.getRunFontSelector().fontSelector(pPr,rPr,((Text)o));
      XmlUtils.treeCopy(df,currentSpan);
    }
  }
 else   if (o instanceof org.docx4j.wml.R.Tab) {
    convertTabToNode(conversionContext,document);
  }
 else   if (o instanceof org.docx4j.wml.CTSimpleField) {
    convertToNode(conversionContext,o,AbstractFldSimpleWriter.WRITER_ID,document,getCurrentParent());
  }
 else   if (o instanceof org.docx4j.wml.P.Hyperlink) {
    convertToNode(conversionContext,o,AbstractHyperlinkWriter.WRITER_ID,document,getCurrentParent());
  }
 else   if (o instanceof org.docx4j.wml.CTBookmark) {
    convertToNode(conversionContext,o,AbstractBookmarkStartWriter.WRITER_ID,document,getCurrentParent());
  }
 else   if (o instanceof org.docx4j.wml.Tbl) {
    convertToNode(conversionContext,o,AbstractTableWriter.WRITER_ID,document,(currentP != null ? currentP : parentNode));
    currentP=null;
    currentSpan=null;
  }
 else   if (o instanceof org.docx4j.wml.Tr) {
  }
 else   if (o instanceof org.docx4j.wml.Tc) {
  }
 else   if (o instanceof org.docx4j.dml.wordprocessingDrawing.Inline || o instanceof org.docx4j.dml.wordprocessingDrawing.Anchor) {
    anchorOrInline=o;
  }
 else   if (o instanceof org.docx4j.dml.CTBlip) {
    DocumentFragment foreignFragment=createImage(IMAGE_E20,conversionContext,anchorOrInline);
    anchorOrInline=null;
    currentP.appendChild(document.importNode(foreignFragment,true));
  }
 else   if (o instanceof org.docx4j.wml.Pict) {
    org.docx4j.vml.CTTextbox textBox=getTextBox((org.docx4j.wml.Pict)o);
    if (textBox == null) {
      DocumentFragment foreignFragment=createImage(IMAGE_E10,conversionContext,o);
      currentP.appendChild(document.importNode(foreignFragment,true));
    }
 else {
      convertToNode(conversionContext,o,AbstractPictWriter.WRITER_ID,document,getCurrentParent());
    }
  }
 else   if (o instanceof Br) {
    handleBr((Br)o);
  }
 else   if (o instanceof org.docx4j.wml.R.Sym) {
    convertToNode(conversionContext,o,AbstractSymbolWriter.WRITER_ID,document,getCurrentParent());
  }
 else   if ((o instanceof org.docx4j.wml.ProofErr) || (o instanceof org.docx4j.wml.R.LastRenderedPageBreak) || (o instanceof org.docx4j.wml.CTMarkupRange)) {
  }
 else {
    log.warn("Need to handle " + o.getClass().getName());
    if (log.isDebugEnabled()) {
      log.debug(XmlUtils.marshaltoString(o));
    }
  }
  return null;
}
