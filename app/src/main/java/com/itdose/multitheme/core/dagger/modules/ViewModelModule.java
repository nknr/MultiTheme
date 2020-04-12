package com.itdose.multitheme.core.dagger.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.itdose.multitheme.utils.ViewModelFactory;
import com.itdose.multitheme.viewmodel.PeopleViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PeopleViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsPeopleViewModel(PeopleViewModel viewModel);

/*
    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsRegistrationViewModel(RegistrationViewModel registrationViewModel);*/


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

}
