public long lastModified() throws SmbException {
switch (mode) {
case SFTP:
    SshClientUtils.execute(new SFtpClientTemplate(path){
      @Override public Long execute(      SFTPClient client) throws IOException {
        return client.mtime(SshClientUtils.extractRemotePathFrom(path));
      }
    }
);
  break;
case SMB:
SmbFile smbFile=getSmbFile();
if (smbFile != null) return smbFile.lastModified();
break;
case FILE:
new File(path).lastModified();
break;
case ROOT:
HybridFileParcelable baseFile=generateBaseFileFromParent();
if (baseFile != null) return baseFile.getDate();
}
return new File("/").lastModified();
}
