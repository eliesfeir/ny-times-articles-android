package com.android.nytimes.network;

import com.android.nytimes.models.NewsResponse;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class NewsRepositoryTest {

    private static NewsRepository newsRepository;

    private NewsApi newsApi;

    private NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }


    @Before
    public void initialize() {
        newsApi = getInstance().getNewsApi();
    }

    @Test
    public void getArticles() {

        Call<NewsResponse> call = newsApi.getNewsList();
        try {
            Response<NewsResponse> response = call.execute();
            assertTrue(response.isSuccessful());
        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }

}