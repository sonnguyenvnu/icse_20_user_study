/** 
 * Initializes the texture using GL parameters
 */
public void init(int width,int height,int glName,int glTarget,int glFormat,int glWidth,int glHeight,int glMinFilter,int glMagFilter,int glWrapS,int glWrapT){
  this.width=width;
  this.height=height;
  this.glName=glName;
  this.glTarget=glTarget;
  this.glFormat=glFormat;
  this.glWidth=glWidth;
  this.glHeight=glHeight;
  this.glMinFilter=glMinFilter;
  this.glMagFilter=glMagFilter;
  this.glWrapS=glWrapS;
  this.glWrapT=glWrapT;
  maxTexcoordU=(float)width / glWidth;
  maxTexcoordV=(float)height / glHeight;
  usingMipmaps=glMinFilter == PGL.LINEAR_MIPMAP_NEAREST || glMinFilter == PGL.LINEAR_MIPMAP_LINEAR;
  usingRepeat=glWrapS == PGL.REPEAT || glWrapT == PGL.REPEAT;
}
