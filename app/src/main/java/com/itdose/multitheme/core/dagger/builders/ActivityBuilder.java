package com.itdose.multitheme.core.dagger.builders;



import com.itdose.multitheme.view.activity.PeopleActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector()
    @SuppressWarnings("unused")
    abstract PeopleActivity contributePeopleActivity();

/*    @ContributesAndroidInjector()
    @SuppressWarnings("unused")
    abstract LoginActivity contributeLoginActivity();*/

}
