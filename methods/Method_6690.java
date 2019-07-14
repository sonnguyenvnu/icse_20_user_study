public static void loadGalleryPhotosAlbums(final int guid){
  Thread thread=new Thread(() -> {
    final ArrayList<AlbumEntry> mediaAlbumsSorted=new ArrayList<>();
    final ArrayList<AlbumEntry> photoAlbumsSorted=new ArrayList<>();
    SparseArray<AlbumEntry> mediaAlbums=new SparseArray<>();
    SparseArray<AlbumEntry> photoAlbums=new SparseArray<>();
    AlbumEntry allPhotosAlbum=null;
    AlbumEntry allVideosAlbum=null;
    AlbumEntry allMediaAlbum=null;
    String cameraFolder=null;
    try {
      cameraFolder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/" + "Camera/";
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    Integer mediaCameraAlbumId=null;
    Integer photoCameraAlbumId=null;
    Cursor cursor=null;
    try {
      if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT >= 23 && ApplicationLoader.applicationContext.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        cursor=MediaStore.Images.Media.query(ApplicationLoader.applicationContext.getContentResolver(),MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projectionPhotos,null,null,MediaStore.Images.Media.DATE_TAKEN + " DESC");
        if (cursor != null) {
          int imageIdColumn=cursor.getColumnIndex(MediaStore.Images.Media._ID);
          int bucketIdColumn=cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
          int bucketNameColumn=cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
          int dataColumn=cursor.getColumnIndex(MediaStore.Images.Media.DATA);
          int dateColumn=cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
          int orientationColumn=cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION);
          while (cursor.moveToNext()) {
            int imageId=cursor.getInt(imageIdColumn);
            int bucketId=cursor.getInt(bucketIdColumn);
            String bucketName=cursor.getString(bucketNameColumn);
            String path=cursor.getString(dataColumn);
            long dateTaken=cursor.getLong(dateColumn);
            int orientation=cursor.getInt(orientationColumn);
            if (path == null || path.length() == 0) {
              continue;
            }
            PhotoEntry photoEntry=new PhotoEntry(bucketId,imageId,dateTaken,path,orientation,false);
            if (allPhotosAlbum == null) {
              allPhotosAlbum=new AlbumEntry(0,LocaleController.getString("AllPhotos",R.string.AllPhotos),photoEntry);
              photoAlbumsSorted.add(0,allPhotosAlbum);
            }
            if (allMediaAlbum == null) {
              allMediaAlbum=new AlbumEntry(0,LocaleController.getString("AllMedia",R.string.AllMedia),photoEntry);
              mediaAlbumsSorted.add(0,allMediaAlbum);
            }
            allPhotosAlbum.addPhoto(photoEntry);
            allMediaAlbum.addPhoto(photoEntry);
            AlbumEntry albumEntry=mediaAlbums.get(bucketId);
            if (albumEntry == null) {
              albumEntry=new AlbumEntry(bucketId,bucketName,photoEntry);
              mediaAlbums.put(bucketId,albumEntry);
              if (mediaCameraAlbumId == null && cameraFolder != null && path != null && path.startsWith(cameraFolder)) {
                mediaAlbumsSorted.add(0,albumEntry);
                mediaCameraAlbumId=bucketId;
              }
 else {
                mediaAlbumsSorted.add(albumEntry);
              }
            }
            albumEntry.addPhoto(photoEntry);
            albumEntry=photoAlbums.get(bucketId);
            if (albumEntry == null) {
              albumEntry=new AlbumEntry(bucketId,bucketName,photoEntry);
              photoAlbums.put(bucketId,albumEntry);
              if (photoCameraAlbumId == null && cameraFolder != null && path != null && path.startsWith(cameraFolder)) {
                photoAlbumsSorted.add(0,albumEntry);
                photoCameraAlbumId=bucketId;
              }
 else {
                photoAlbumsSorted.add(albumEntry);
              }
            }
            albumEntry.addPhoto(photoEntry);
          }
        }
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
 finally {
      if (cursor != null) {
        try {
          cursor.close();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
    try {
      if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT >= 23 && ApplicationLoader.applicationContext.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        cursor=MediaStore.Images.Media.query(ApplicationLoader.applicationContext.getContentResolver(),MediaStore.Video.Media.EXTERNAL_CONTENT_URI,projectionVideo,null,null,MediaStore.Video.Media.DATE_TAKEN + " DESC");
        if (cursor != null) {
          int imageIdColumn=cursor.getColumnIndex(MediaStore.Video.Media._ID);
          int bucketIdColumn=cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_ID);
          int bucketNameColumn=cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
          int dataColumn=cursor.getColumnIndex(MediaStore.Video.Media.DATA);
          int dateColumn=cursor.getColumnIndex(MediaStore.Video.Media.DATE_TAKEN);
          int durationColumn=cursor.getColumnIndex(MediaStore.Video.Media.DURATION);
          while (cursor.moveToNext()) {
            int imageId=cursor.getInt(imageIdColumn);
            int bucketId=cursor.getInt(bucketIdColumn);
            String bucketName=cursor.getString(bucketNameColumn);
            String path=cursor.getString(dataColumn);
            long dateTaken=cursor.getLong(dateColumn);
            long duration=cursor.getLong(durationColumn);
            if (path == null || path.length() == 0) {
              continue;
            }
            PhotoEntry photoEntry=new PhotoEntry(bucketId,imageId,dateTaken,path,(int)(duration / 1000),true);
            if (allVideosAlbum == null) {
              allVideosAlbum=new AlbumEntry(0,LocaleController.getString("AllVideos",R.string.AllVideos),photoEntry);
              int index=0;
              if (allMediaAlbum != null) {
                index++;
              }
              if (allPhotosAlbum != null) {
                index++;
              }
              mediaAlbumsSorted.add(index,allVideosAlbum);
            }
            if (allMediaAlbum == null) {
              allMediaAlbum=new AlbumEntry(0,LocaleController.getString("AllMedia",R.string.AllMedia),photoEntry);
              mediaAlbumsSorted.add(0,allMediaAlbum);
            }
            allVideosAlbum.addPhoto(photoEntry);
            allMediaAlbum.addPhoto(photoEntry);
            AlbumEntry albumEntry=mediaAlbums.get(bucketId);
            if (albumEntry == null) {
              albumEntry=new AlbumEntry(bucketId,bucketName,photoEntry);
              mediaAlbums.put(bucketId,albumEntry);
              if (mediaCameraAlbumId == null && cameraFolder != null && path != null && path.startsWith(cameraFolder)) {
                mediaAlbumsSorted.add(0,albumEntry);
                mediaCameraAlbumId=bucketId;
              }
 else {
                mediaAlbumsSorted.add(albumEntry);
              }
            }
            albumEntry.addPhoto(photoEntry);
          }
        }
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
 finally {
      if (cursor != null) {
        try {
          cursor.close();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
    for (int a=0; a < mediaAlbumsSorted.size(); a++) {
      Collections.sort(mediaAlbumsSorted.get(a).photos,(o1,o2) -> {
        if (o1.dateTaken < o2.dateTaken) {
          return 1;
        }
 else         if (o1.dateTaken > o2.dateTaken) {
          return -1;
        }
        return 0;
      }
);
    }
    broadcastNewPhotos(guid,mediaAlbumsSorted,photoAlbumsSorted,mediaCameraAlbumId,allMediaAlbum,allPhotosAlbum,allVideosAlbum,0);
  }
);
  thread.setPriority(Thread.MIN_PRIORITY);
  thread.start();
}
