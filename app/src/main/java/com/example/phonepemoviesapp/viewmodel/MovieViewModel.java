package com.example.phonepemoviesapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.example.MovieData;
import com.example.phonepemoviesapp.network.MovieRepository;
import com.example.phonepemoviesapp.util.MainConstant;
import com.google.gson.Gson;

public class MovieViewModel extends AndroidViewModel {
    private Application application;

    private final String TAG = MovieViewModel.class.getSimpleName();
    private final MovieRepository mRepository;
    private MutableLiveData<MovieData> mResponse;

    public MovieViewModel(Application application) {
        super(application);
        mRepository = new MovieRepository(application);
    }

    public LiveData<MovieData> hitApi() {
        String  url = MainConstant.url;
        mResponse = new MutableLiveData<>();
        mRepository.hitApi(url, (stringData, response) -> {
            try {
                MovieData movieResponse = new Gson().fromJson(stringData, MovieData.class);
                mResponse.setValue(movieResponse);
            } catch (Exception e) {
                e.printStackTrace();
                mResponse.setValue(null);
            }
        });
        return mResponse;
    }
}
