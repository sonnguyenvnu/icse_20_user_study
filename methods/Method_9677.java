private void setCurrentImage(boolean setThumb){
  if (currentWallpaper instanceof TLRPC.TL_wallPaper) {
    TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)currentWallpaper;
    TLRPC.PhotoSize thumb=setThumb ? FileLoader.getClosestPhotoSizeWithSize(wallPaper.document.thumbs,100) : null;
    backgroundImage.setImage(ImageLocation.getForDocument(wallPaper.document),imageFilter,ImageLocation.getForDocument(thumb,wallPaper.document),"100_100_b","jpg",wallPaper.document.size,1,wallPaper);
  }
 else   if (currentWallpaper instanceof WallpapersListActivity.ColorWallpaper) {
    WallpapersListActivity.ColorWallpaper wallPaper=(WallpapersListActivity.ColorWallpaper)currentWallpaper;
    setBackgroundColor(wallPaper.color);
    if (selectedPattern != null) {
      backgroundImage.setImage(ImageLocation.getForDocument(selectedPattern.document),imageFilter,null,null,"jpg",selectedPattern.document.size,1,selectedPattern);
    }
  }
 else   if (currentWallpaper instanceof WallpapersListActivity.FileWallpaper) {
    if (currentWallpaperBitmap != null) {
      backgroundImage.setImageBitmap(currentWallpaperBitmap);
    }
 else {
      WallpapersListActivity.FileWallpaper wallPaper=(WallpapersListActivity.FileWallpaper)currentWallpaper;
      if (wallPaper.originalPath != null) {
        backgroundImage.setImage(wallPaper.originalPath.getAbsolutePath(),imageFilter,null);
      }
 else       if (wallPaper.path != null) {
        backgroundImage.setImage(wallPaper.path.getAbsolutePath(),imageFilter,null);
      }
 else       if (wallPaper.resId == Theme.THEME_BACKGROUND_ID) {
        backgroundImage.setImageDrawable(Theme.getThemedWallpaper(false));
      }
 else       if (wallPaper.resId != 0) {
        backgroundImage.setImageResource(wallPaper.resId);
      }
    }
  }
 else   if (currentWallpaper instanceof MediaController.SearchImage) {
    MediaController.SearchImage wallPaper=(MediaController.SearchImage)currentWallpaper;
    if (wallPaper.photo != null) {
      TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(wallPaper.photo.sizes,100);
      TLRPC.PhotoSize image=FileLoader.getClosestPhotoSizeWithSize(wallPaper.photo.sizes,maxWallpaperSize,true);
      if (image == thumb) {
        image=null;
      }
      int size=image != null ? image.size : 0;
      backgroundImage.setImage(ImageLocation.getForPhoto(image,wallPaper.photo),imageFilter,ImageLocation.getForPhoto(thumb,wallPaper.photo),"100_100_b","jpg",size,1,wallPaper);
    }
 else {
      backgroundImage.setImage(wallPaper.imageUrl,imageFilter,wallPaper.thumbUrl,"100_100_b");
    }
  }
}
