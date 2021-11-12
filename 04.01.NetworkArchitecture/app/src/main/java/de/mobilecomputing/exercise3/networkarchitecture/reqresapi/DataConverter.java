package de.mobilecomputing.exercise3.networkarchitecture.reqresapi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class DataConverter<T> implements Converter<ResponseBody, T>
{
    final private Converter<ResponseBody, Data<T>> mConverter;

    public DataConverter(Converter<ResponseBody, Data<T>> converter) {
        mConverter = converter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        Data<T> dataModel = mConverter.convert(value);
        return dataModel.data;
    }
}