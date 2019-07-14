/** 
 * Import the given stream data into the text component.
 */
protected void handleReaderImport(Reader in,JTextComponent c,boolean useRead) throws BadLocationException, IOException {
  if (useRead) {
    int startPosition=c.getSelectionStart();
    int endPosition=c.getSelectionEnd();
    int length=endPosition - startPosition;
    EditorKit kit=c.getUI().getEditorKit(c);
    Document doc=c.getDocument();
    if (length > 0) {
      doc.remove(startPosition,length);
    }
    kit.read(in,doc,startPosition);
  }
 else {
    char[] buff=new char[1024];
    int nch;
    boolean lastWasCR=false;
    int last;
    StringBuilder sb=null;
    while ((nch=in.read(buff,0,buff.length)) != -1) {
      if (sb == null) {
        sb=new StringBuilder(nch);
      }
      last=0;
      for (int counter=0; counter < nch; counter++) {
switch (buff[counter]) {
case '\r':
          if (lastWasCR) {
            if (counter == 0) {
              sb.append('\n');
            }
 else {
              buff[counter - 1]='\n';
            }
          }
 else {
            lastWasCR=true;
          }
        break;
case '\n':
      if (lastWasCR) {
        if (counter > (last + 1)) {
          sb.append(buff,last,counter - last - 1);
        }
        lastWasCR=false;
        last=counter;
      }
    break;
default :
  if (lastWasCR) {
    if (counter == 0) {
      sb.append('\n');
    }
 else {
      buff[counter - 1]='\n';
    }
    lastWasCR=false;
  }
break;
}
}
if (last < nch) {
if (lastWasCR) {
if (last < (nch - 1)) {
sb.append(buff,last,nch - last - 1);
}
}
 else {
sb.append(buff,last,nch - last);
}
}
}
if (lastWasCR) {
sb.append('\n');
}
System.out.println("FileTextTransferHandler " + c.getSelectionStart() + ".." + c.getSelectionEnd());
c.replaceSelection(sb != null ? sb.toString() : "");
}
}
