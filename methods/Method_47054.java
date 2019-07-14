public OutputStream getOutputStream(Context context){
  OutputStream outputStream;
switch (mode) {
case SFTP:
    return SshClientUtils.execute(new SshClientTemplate(path,false){
      @Override public OutputStream execute(      final SSHClient ssh) throws IOException {
        final SFTPClient client=ssh.newSFTPClient();
        final RemoteFile rf=client.open(SshClientUtils.extractRemotePathFrom(path),EnumSet.of(net.schmizz.sshj.sftp.OpenMode.WRITE,net.schmizz.sshj.sftp.OpenMode.CREAT));
        return rf.new RemoteFileOutputStream(){
          @Override public void close() throws IOException {
            try {
              super.close();
            }
  finally {
              rf.close();
              client.close();
            }
          }
        }
;
      }
    }
);
case SMB:
  try {
    outputStream=new SmbFile(path).getOutputStream();
  }
 catch (  IOException e) {
    outputStream=null;
    e.printStackTrace();
  }
break;
case OTG:
ContentResolver contentResolver=context.getContentResolver();
DocumentFile documentSourceFile=OTGUtil.getDocumentFile(path,context,true);
try {
outputStream=contentResolver.openOutputStream(documentSourceFile.getUri());
}
 catch (FileNotFoundException e) {
e.printStackTrace();
outputStream=null;
}
break;
default :
try {
outputStream=FileUtil.getOutputStream(new File(path),context);
}
 catch (Exception e) {
outputStream=null;
e.printStackTrace();
}
}
return outputStream;
}
