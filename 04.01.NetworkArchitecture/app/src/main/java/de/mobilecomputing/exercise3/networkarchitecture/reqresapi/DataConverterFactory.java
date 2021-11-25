package de.mobilecomputing.exercise3.networkarchitecture.reqresapi;

import androidx.annotation.Nullable;

import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import de.mobilecomputing.exercise3.networkarchitecture.models.User;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.POST;

public class DataConverterFactory extends Converter.Factory {

    @SuppressWarnings("unchecked")
    @Nullable
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit)
    {
        try {
            if(annotations[0].annotationType() == POST.class) {
                Type dataType = TypeToken.getParameterized(User.class, type).getType();
                Converter<ResponseBody, User> converter = retrofit.nextResponseBodyConverter(this, dataType, annotations);

                return converter;
            }

            // Use TypeToken of Gson to get type literal for the parameterized type represented Data<T>
            Type dataType = TypeToken.getParameterized(Data.class, type).getType();
            Converter<ResponseBody, Data> converter = retrofit.nextResponseBodyConverter(this, dataType, annotations);

            return new DataConverter(converter);
        } catch (Exception e) {
            return null;
        }
    }
}
