package com.vondear.rxtool;

import android.location.Location;
import android.media.ExifInterface;

import com.orhanobut.logger.Logger;

import java.io.File;

/**
 *
 * @author Vondear
 * @date 2017/7/28
 *
 */

public class RxExifTool {
    /**
     * 将�?纬度信�?�写入JPEG图片文件里
     *
     * @param picPath JPEG图片文件路径
     * @param dLat    纬度
     * @param dLon    �?度
     */
    public static void writeLatLonIntoJpeg(String picPath, double dLat, double dLon) {
        File file = new File(picPath);
        if (file.exists()) {
            try {
                ExifInterface exif = new ExifInterface(picPath);
                String tagLat = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
                String tagLon = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
                if (tagLat == null && tagLon == null) {// 无�?纬度信�?�
                    exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, gpsInfoConvert(dLat));
                    exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, dLat > 0 ? "N" : "S"); // 区分�?�北�?��?�
                    exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, gpsInfoConvert(dLon));
                    exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, dLon > 0 ? "E" : "W"); // 区分东�?西�?
                    exif.saveAttributes();
                }
                exif.saveAttributes();

                Logger.d(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE) + "\n"
                        + exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE) + "\n"
                        + exif.getAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD) + "\n"
                        + exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH) + "\n"
                        + exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH));
            } catch (Exception e) {

            }
        }
    }

    private static String gpsInfoConvert(double gpsInfo){
        gpsInfo = Math.abs(gpsInfo);
        String dms = Location.convert(gpsInfo, Location.FORMAT_SECONDS);
        String[] splits = dms.split(":");
        String[] secnds = (splits[2]).split("\\.");
        String seconds;
        if (secnds.length == 0) {
            seconds = splits[2];
        } else {
            seconds = secnds[0];
        }
        return  splits[0] + "/1," + splits[1] + "/1," + seconds + "/1";
    }
}
