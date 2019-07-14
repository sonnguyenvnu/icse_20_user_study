void data(MP4Atom atom) throws IOException {
  if (LOGGER.isLoggable(debugLevel)) {
    LOGGER.log(debugLevel,atom.toString());
  }
  atom.skip(4);
  atom.skip(4);
switch (atom.getParent().getType()) {
case "©alb":
    album=atom.readString(UTF_8);
  break;
case "aART":
albumArtist=atom.readString(UTF_8);
break;
case "©ART":
artist=atom.readString(UTF_8);
break;
case "©cmt":
comment=atom.readString(UTF_8);
break;
case "©com":
case "©wrt":
if (composer == null || composer.trim().length() == 0) {
composer=atom.readString(UTF_8);
}
break;
case "covr":
try {
byte[] bytes=atom.readBytes();
BitmapFactory.Options opts=new BitmapFactory.Options();
opts.inJustDecodeBounds=true;
opts.inSampleSize=1;
BitmapFactory.decodeByteArray(bytes,0,bytes.length,opts);
if (opts.outWidth > 800 || opts.outHeight > 800) {
int size=Math.max(opts.outWidth,opts.outHeight);
while (size > 800) {
opts.inSampleSize*=2;
size/=2;
}
}
opts.inJustDecodeBounds=false;
cover=BitmapFactory.decodeByteArray(bytes,0,bytes.length,opts);
if (cover != null) {
float scale=Math.max(cover.getWidth(),cover.getHeight()) / 120.0f;
if (scale > 0) {
smallCover=Bitmap.createScaledBitmap(cover,(int)(cover.getWidth() / scale),(int)(cover.getHeight() / scale),true);
}
 else {
smallCover=cover;
}
if (smallCover == null) {
smallCover=cover;
}
}
}
 catch (Exception e) {
e.printStackTrace();
}
break;
case "cpil":
compilation=atom.readBoolean();
break;
case "cprt":
case "©cpy":
if (copyright == null || copyright.trim().length() == 0) {
copyright=atom.readString(UTF_8);
}
break;
case "©day":
String day=atom.readString(UTF_8).trim();
if (day.length() >= 4) {
try {
year=Short.valueOf(day.substring(0,4));
}
 catch (NumberFormatException e) {
}
}
break;
case "disk":
atom.skip(2);
disc=atom.readShort();
discs=atom.readShort();
break;
case "gnre":
if (genre == null || genre.trim().length() == 0) {
if (atom.getRemaining() == 2) {
int index=atom.readShort() - 1;
ID3v1Genre id3v1Genre=ID3v1Genre.getGenre(index);
if (id3v1Genre != null) {
genre=id3v1Genre.getDescription();
}
}
 else {
genre=atom.readString(UTF_8);
}
}
break;
case "©gen":
if (genre == null || genre.trim().length() == 0) {
genre=atom.readString(UTF_8);
}
break;
case "©grp":
grouping=atom.readString(UTF_8);
break;
case "©lyr":
lyrics=atom.readString(UTF_8);
break;
case "©nam":
title=atom.readString(UTF_8);
break;
case "rtng":
rating=atom.readByte();
break;
case "tmpo":
tempo=atom.readShort();
break;
case "trkn":
atom.skip(2);
track=atom.readShort();
tracks=atom.readShort();
break;
default :
break;
}
}
