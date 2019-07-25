package com.cg.baseproject.request.retrofit.factory;

import com.cg.baseproject.request.retrofit.converter.FastJsonRequestBodyConverter;
import com.cg.baseproject.request.retrofit.converter.FastJsonResponseBodyConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * <b>类�??称：</b> FastJsonConverterFactory <br/>
 * <b>类�??述：</b> FastJsonCoverter<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年03月08日 下�?�3:48<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class FastJsonConverterFactory extends Converter.Factory{

    public static FastJsonConverterFactory create() {
        return new FastJsonConverterFactory();
    }

    /**
     * 需�?�?写父类中responseBodyConverter，该方法用�?�转�?��?务器返回数�?�
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastJsonResponseBodyConverter<>(type);
    }

    /**
     * 需�?�?写父类中responseBodyConverter，该方法用�?�转�?��?��?给�?务器的数�?�
     */
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestBodyConverter<>();
    }
}




