package com.marvelcomics.brito.data.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marvelcomics.brito.data.datasource.remote.response.SeriesResponse;
import com.marvelcomics.brito.data.datasource.remote.response.model.RemoteMarvelContainer;
import com.marvelcomics.brito.entity.SeriesEntity;
import com.marvelcomics.brito.infrastructure.exception.MarvelApiException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class SeriesEntityMapperTest {

    /*private RemoteMarvelContainer<SeriesResponse> backendResponse;

    @Before
    public void mockSeriesJson() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("series.json");
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String string;

        while ((string = in.readLine()) != null) {
            stringBuilder.append(string);
        }

        in.close();

        Gson gson = new Gson();
        Type type = new TypeToken<RemoteMarvelContainer<SeriesResponse>>() {}.getType();
        backendResponse = gson.fromJson(stringBuilder.toString(), type);
    }

    @Test
    public void seriesEntityMapper_transform_shouldTransformObject() throws MarvelApiException {
        SeriesEntity series = SeriesEntityMapper.transform(backendResponse).get(0);
        assertThat("Object should return list off " + SeriesEntity.class.getCanonicalName(),
                series, instanceOf(SeriesEntity.class));
    }

    @Test//(expected = MarvelApiException.class)
    public void seriesEntityMapper_transform_shouldThrowException() throws MarvelApiException {
        when(SeriesEntityMapper.transform(backendResponse)).thenThrow(MarvelApiException.class);
        SeriesEntity series = SeriesEntityMapper.transform(backendResponse).get(0);
    }*/
}
