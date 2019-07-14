private TLRPC.RichText getBlockCaption(TLRPC.PageBlock block,int type){
  if (type == 2) {
    TLRPC.RichText text1=getBlockCaption(block,0);
    if (text1 instanceof TLRPC.TL_textEmpty) {
      text1=null;
    }
    TLRPC.RichText text2=getBlockCaption(block,1);
    if (text2 instanceof TLRPC.TL_textEmpty) {
      text2=null;
    }
    if (text1 != null && text2 == null) {
      return text1;
    }
 else     if (text1 == null && text2 != null) {
      return text2;
    }
 else     if (text1 != null && text2 != null) {
      TLRPC.TL_textPlain text3=new TLRPC.TL_textPlain();
      text3.text=" ";
      TLRPC.TL_textConcat textConcat=new TLRPC.TL_textConcat();
      textConcat.texts.add(text1);
      textConcat.texts.add(text3);
      textConcat.texts.add(text2);
      return textConcat;
    }
 else {
      return null;
    }
  }
  if (block instanceof TLRPC.TL_pageBlockEmbedPost) {
    TLRPC.TL_pageBlockEmbedPost blockEmbedPost=(TLRPC.TL_pageBlockEmbedPost)block;
    if (type == 0) {
      return blockEmbedPost.caption.text;
    }
 else     if (type == 1) {
      return blockEmbedPost.caption.credit;
    }
  }
 else   if (block instanceof TLRPC.TL_pageBlockSlideshow) {
    TLRPC.TL_pageBlockSlideshow pageBlockSlideshow=(TLRPC.TL_pageBlockSlideshow)block;
    if (type == 0) {
      return pageBlockSlideshow.caption.text;
    }
 else     if (type == 1) {
      return pageBlockSlideshow.caption.credit;
    }
  }
 else   if (block instanceof TLRPC.TL_pageBlockPhoto) {
    TLRPC.TL_pageBlockPhoto pageBlockPhoto=(TLRPC.TL_pageBlockPhoto)block;
    if (type == 0) {
      return pageBlockPhoto.caption.text;
    }
 else     if (type == 1) {
      return pageBlockPhoto.caption.credit;
    }
  }
 else   if (block instanceof TLRPC.TL_pageBlockCollage) {
    TLRPC.TL_pageBlockCollage pageBlockCollage=(TLRPC.TL_pageBlockCollage)block;
    if (type == 0) {
      return pageBlockCollage.caption.text;
    }
 else     if (type == 1) {
      return pageBlockCollage.caption.credit;
    }
  }
 else   if (block instanceof TLRPC.TL_pageBlockEmbed) {
    TLRPC.TL_pageBlockEmbed pageBlockEmbed=(TLRPC.TL_pageBlockEmbed)block;
    if (type == 0) {
      return pageBlockEmbed.caption.text;
    }
 else     if (type == 1) {
      return pageBlockEmbed.caption.credit;
    }
  }
 else   if (block instanceof TLRPC.TL_pageBlockBlockquote) {
    TLRPC.TL_pageBlockBlockquote pageBlockBlockquote=(TLRPC.TL_pageBlockBlockquote)block;
    return pageBlockBlockquote.caption;
  }
 else   if (block instanceof TLRPC.TL_pageBlockVideo) {
    TLRPC.TL_pageBlockVideo pageBlockVideo=(TLRPC.TL_pageBlockVideo)block;
    if (type == 0) {
      return pageBlockVideo.caption.text;
    }
 else     if (type == 1) {
      return pageBlockVideo.caption.credit;
    }
  }
 else   if (block instanceof TLRPC.TL_pageBlockPullquote) {
    TLRPC.TL_pageBlockPullquote pageBlockPullquote=(TLRPC.TL_pageBlockPullquote)block;
    return pageBlockPullquote.caption;
  }
 else   if (block instanceof TLRPC.TL_pageBlockAudio) {
    TLRPC.TL_pageBlockAudio pageBlockAudio=(TLRPC.TL_pageBlockAudio)block;
    if (type == 0) {
      return pageBlockAudio.caption.text;
    }
 else     if (type == 1) {
      return pageBlockAudio.caption.credit;
    }
  }
 else   if (block instanceof TLRPC.TL_pageBlockCover) {
    TLRPC.TL_pageBlockCover pageBlockCover=(TLRPC.TL_pageBlockCover)block;
    return getBlockCaption(pageBlockCover.cover,type);
  }
 else   if (block instanceof TLRPC.TL_pageBlockMap) {
    TLRPC.TL_pageBlockMap pageBlockMap=(TLRPC.TL_pageBlockMap)block;
    if (type == 0) {
      return pageBlockMap.caption.text;
    }
 else     if (type == 1) {
      return pageBlockMap.caption.credit;
    }
  }
  return null;
}
