/** 
 * @deprecated use {@link #length(Context)} to handle content resolvers
 */
public long length(){
  long s=0L;
switch (mode) {
case SFTP:
    return SshClientUtils.execute(new SFtpClientTemplate(path){
      @Override public Long execute(      SFTPClient client) throws IOException {
        return client.size(SshClientUtils.extractRemotePathFrom(path));
      }
    }
);
case SMB:
  SmbFile smbFile=getSmbFile();
if (smbFile != null) try {
  s=smbFile.length();
}
 catch (SmbException e) {
}
return s;
case FILE:
s=new File(path).length();
return s;
case ROOT:
HybridFileParcelable baseFile=generateBaseFileFromParent();
if (baseFile != null) return baseFile.getSize();
break;
}
return s;
}
