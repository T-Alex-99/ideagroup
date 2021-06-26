package com.example.ideaapp.ui.groupGallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ideaapp.model.IdeaGroup;
import com.example.ideaapp.ws.InfrastructureWebservice;

import java.util.ArrayList;

public class GroupGalleryViewModel extends ViewModel {

    private ArrayList<String> state;
    InfrastructureWebservice service = null;

    public GroupGalleryViewModel() {
        service = new InfrastructureWebservice();

    }

}