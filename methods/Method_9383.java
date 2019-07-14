private void addDocumentViewInternal(TLRPC.TL_secureFile f,int uploadingType){
  SecureDocumentKey secureDocumentKey=getSecureDocumentKey(f.secret,f.file_hash);
  SecureDocument secureDocument=new SecureDocument(secureDocumentKey,f,null,null,null);
  addDocumentView(secureDocument,uploadingType);
}
