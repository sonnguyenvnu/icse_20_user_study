package com.cgfay.filter.glfilter.stickers;

import com.badlogic.gdx.math.Camera;
import com.badlogic.gdx.math.Vector3;
import com.cgfay.filter.glfilter.base.GLImageFilter;

import java.util.List;

/**
 * @author ferrisXu
 * 创建日期：2019/2/26
 * �??述：相机和�?幕�??标转�?�，用于触摸控制贴纸的旋转，平移，缩放�?作
 */
public class GestureHelp {


    /**
     * �?幕�??标转本地�??标
     * @param camera
     * @param screenCoords
     * @return
     */
    public static Vector3 screenToStageCoordinates (Camera camera, final Vector3 screenCoords) {
        camera.unproject(screenCoords);
        return screenCoords;
    }

    /**
     * 本地�??标转�?幕�??标
     * @param camera
     * @param stageCoords
     * @return
     */
    public static Vector3 stageToScreenCoordinates (final Camera camera,final Vector3 stageCoords) {
        camera.project(stageCoords);
        stageCoords.y = camera.getScreenHeight() - stageCoords.y;
        return stageCoords;
    }


    public static StaticStickerNormalFilter hit(final Vector3 target,final List<GLImageFilter> mFilters){
        for(GLImageFilter glImageFilter:mFilters){
            if(glImageFilter instanceof StaticStickerNormalFilter){
                StaticStickerNormalFilter staticStickerNormalFilter=((StaticStickerNormalFilter)glImageFilter);
                //�?幕�??标转本地�??标
                screenToStageCoordinates(staticStickerNormalFilter.camera,target);
                //获�?�触摸到的贴纸
                return  ((StaticStickerNormalFilter)glImageFilter).hit(target);
            }
        }
        return null;
    }
}
